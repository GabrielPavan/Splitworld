package com.example.splitworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitworld.database.dao.MemberDAO;
import com.example.splitworld.database.dao.TransactionsBetweenMembersHeadersDAO;
import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;
import com.example.splitworld.util.SharedKey;
import com.example.splitworld.util.adapters.MemberAdapter;

import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private MemberAdapter memberAdapter;
    private MemberDAO memberDAO;
    private TransactionsBetweenMembersHeadersDAO transactionsBetweenMembersHeadersDAO;
    private List<MemberModel> memberList;
    private TextView textViewTotalTravelers, textViewTotalCost, textViewCostPerPerson;
    private double totalAmount = 0.0;
    private Button buttonBack, buttonEndTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        textViewTotalTravelers = findViewById(R.id.textViewTotalTravelers);
        textViewTotalCost = findViewById(R.id.textViewTotalCost);
        textViewCostPerPerson = findViewById(R.id.textViewCostPerPerson);
        buttonBack = findViewById(R.id.buttonBack);
        buttonEndTrip = findViewById(R.id.buttonEndTrip);

        memberDAO = new MemberDAO(this);
        transactionsBetweenMembersHeadersDAO = new TransactionsBetweenMembersHeadersDAO(this);

        memberList = memberDAO.findAll();
        updateTotalSpent(memberList);
        memberList = memberDAO.findAll();

        RecyclerView recyclerViewMembers = findViewById(R.id.recyclerViewMembers);
        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(this));

        memberAdapter = new MemberAdapter(this, memberList);
        recyclerViewMembers.setAdapter(memberAdapter);

        textViewTotalTravelers.setText("Total Travelers: " + memberList.size());
        setupTotal(transactionsBetweenMembersHeadersDAO.findAll());
        textViewCostPerPerson.setText(String.format("Average cost per Member: %.2f", totalAmount/memberList.size()));

        buttonBack.setOnClickListener(v -> {
            startActivity(new Intent(ReportActivity.this, MainActivity.class));
            finish();
        });
        buttonEndTrip.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("If you want to end the trip, the data will be deleted");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    transactionsBetweenMembersHeadersDAO.deleteAll();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ReportActivity.this);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString(SharedKey.KEY_DESTINY, "");
                    edit.apply();
                    startActivity(new Intent(ReportActivity.this, MainActivity.class));
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(ReportActivity.this, MainActivity.class));
                    finish();
                }
            });
            builder.show();
        });

    }
    private void setupTotal(List<TransactionsBetweenMembersHeadersModel> total){
        totalAmount = 0.0;
        for (TransactionsBetweenMembersHeadersModel transaction : total) {
            totalAmount += transaction.getExpense_total_value();
        }
        textViewTotalCost.setText(String.format("Total Trip Cost: $%.2f", totalAmount));
    }

    private void updateTotalSpent(List<MemberModel> memberList){
        for (MemberModel member : memberList) {
            double totalAmount = transactionsBetweenMembersHeadersDAO.getTotalTransactionsByMemberId(member.getId());
            memberDAO.updateTotalPaid(member.getId(), totalAmount);
        }
    }
}