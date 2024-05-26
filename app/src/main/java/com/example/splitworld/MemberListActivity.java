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
import com.example.splitworld.database.dao.TransactionsBetweenMembersHeadersDAO;
import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;
import com.example.splitworld.util.adapters.MemberAdapter;

import java.util.List;

public class MemberListActivity extends AppCompatActivity implements MemberAdapter.OnMemberDeleteListener, MemberAdapter.OnMemberAddListener{

    private MemberAdapter memberAdapter;
    private MemberDAO memberDAO;
    private TransactionsBetweenMembersHeadersDAO transactionsBetweenMembersHeadersDAO;
    private List<MemberModel> memberList;
    private Button fabAddMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        memberDAO = new MemberDAO(this);
        transactionsBetweenMembersHeadersDAO = new TransactionsBetweenMembersHeadersDAO(this);

        memberList = memberDAO.findAll();
        updateTotalSpent(memberList);
        memberList = memberDAO.findAll();

        fabAddMember = findViewById(R.id.fabAddMember);
        fabAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddMemberDialog();
            }
        });

        RecyclerView recyclerViewMembers = findViewById(R.id.recyclerViewMembers);
        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(this));

        memberAdapter = new MemberAdapter(this, memberList, this, this);
        recyclerViewMembers.setAdapter(memberAdapter);
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
                    onMemberAdd(new MemberModel(memberName, 0.0, 0.0));
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
    private void updateTotalSpent(List<MemberModel> memberList){
        for (MemberModel member : memberList) {
            double totalAmount = transactionsBetweenMembersHeadersDAO.getTotalTransactionsByMemberId(member.getId());
            memberDAO.updateTotalPaid(member.getId(), totalAmount);
        }
    }
    @Override
    public void onMemberDelete(MemberModel member, int position) {
        memberDAO.deleteMember(member.getId());
        memberList.remove(position);
        memberAdapter.notifyItemRemoved(position);
        memberAdapter.notifyItemRangeChanged(position, memberList.size());
    }
    @Override
    public void onMemberAdd(MemberModel member) {
        memberDAO.Insert(member);
        memberList.add(member);
        memberAdapter.notifyItemInserted(memberList.size() - 1);
    }
}
