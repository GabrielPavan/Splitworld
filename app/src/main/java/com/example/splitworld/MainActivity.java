package com.example.splitworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitworld.database.dao.TransactionsBetweenMembersHeadersDAO;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;
import com.example.splitworld.util.SharedKey;
import com.example.splitworld.util.adapters.ExpenseAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textViewDestiny;
    private ImageView iconMembers;
    private ImageView iconAddExpense;
    private ExpenseAdapter expenseAdapter;
    private List<TransactionsBetweenMembersHeadersModel> transactionsBetweenMembersHeadersModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDestiny = findViewById(R.id.textDestiny);
        iconMembers = findViewById(R.id.iconBotton1);
        iconAddExpense = findViewById(R.id.iconBotton2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        textViewDestiny.setText(preferences.getString(SharedKey.KEY_DESTINY, ""));
        textViewDestiny.setOnClickListener(v -> showChangeDestinyDialog());
        if (textViewDestiny.getText().toString().isEmpty()) {
            showChangeDestinyDialog();
        }

        TransactionsBetweenMembersHeadersDAO transactionsBetweenMembersHeadersDAO = new TransactionsBetweenMembersHeadersDAO(this);
        transactionsBetweenMembersHeadersModelList = transactionsBetweenMembersHeadersDAO.findAll();

        setupRecyclerView();

        iconMembers.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MemberListActivity.class)));
        iconAddExpense.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddExpenseActivity.class));
            finish();
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.feedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseAdapter = new ExpenseAdapter(transactionsBetweenMembersHeadersModelList);
        recyclerView.setAdapter(expenseAdapter);
    }

    private void showChangeDestinyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set your destiny: ");

        final EditText editText = new EditText(this);
        editText.setText("");
        builder.setView(editText);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newText = editText.getText().toString();
            if (!newText.isEmpty()) {
                textViewDestiny.setText(newText);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString(SharedKey.KEY_DESTINY, newText);
                edit.apply();
            } else {
                Toast.makeText(MainActivity.this, "Destiny cannot be empty", Toast.LENGTH_SHORT).show();
                showChangeDestinyDialog();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}