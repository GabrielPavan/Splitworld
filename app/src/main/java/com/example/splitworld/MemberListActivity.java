package com.example.splitworld;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitworld.database.dao.MemberDAO;
import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.util.adpters.MemberAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MemberListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMembers;
    private MemberAdapter memberAdapter;
    private List<MemberModel> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        recyclerViewMembers = findViewById(R.id.recyclerViewMembers);
        Button fabAddMember = findViewById(R.id.fabAddMember);

        MemberDAO memberDAO = new MemberDAO(MemberListActivity.this);

        memberList = memberDAO.findAll();
        memberAdapter = new MemberAdapter(memberList);

        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMembers.setAdapter(memberAdapter);

        fabAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddMemberDialog();
            }
        });
    }

    private void showAddMemberDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Member");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String memberName = input.getText().toString();
                if (!memberName.isEmpty()) {
                    MemberDAO memberDAO = new MemberDAO(MemberListActivity.this);
                    MemberModel memberModel = new MemberModel(memberName, 0.0, 0.0);
                    memberDAO.Insert(memberModel);
                    memberList.add(memberModel);
                    memberAdapter.notifyItemInserted(memberList.size() - 1);
                } else {
                    Toast.makeText(MemberListActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
