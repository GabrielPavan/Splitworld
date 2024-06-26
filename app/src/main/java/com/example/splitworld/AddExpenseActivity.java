package com.example.splitworld;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splitworld.database.dao.MemberDAO;
import com.example.splitworld.database.dao.TransactionsBetweenMembersHeadersDAO;
import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;

import java.util.List;

public class AddExpenseActivity extends AppCompatActivity {

    private Spinner spinnerExpenseType;
    private EditText editTextTotalValue;
    private EditText editTextDescription;
    private Spinner spinnerMembers;
    private Button btnAddExpense;
    private Button btnCancelExpense;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        spinnerExpenseType = findViewById(R.id.spinnerExpenseType);
        editTextTotalValue = findViewById(R.id.editTextTotalValue);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerMembers = findViewById(R.id.spinnerMember);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnCancelExpense = findViewById(R.id.btnCancelExpense);

        loadMembers();
        loadExpenseTypes();

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });
        btnCancelExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpenseActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void loadExpenseTypes() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.expense_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExpenseType.setAdapter(adapter);
    }

    private void loadMembers() {
        MemberDAO memberDAO = new MemberDAO(this);
        List<MemberModel> members = memberDAO.findAll();
        ArrayAdapter<MemberModel> adapter = new ArrayAdapter<>(this, R.layout.simple_spinner_dropdown_item, members);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerMembers.setAdapter(adapter);
    }

    private void addExpense() {
        String expenseType = spinnerExpenseType.getSelectedItem().toString();
        String totalValue = editTextTotalValue.getText().toString();
        String description = editTextDescription.getText().toString();
        MemberModel selectedMember = (MemberModel) spinnerMembers.getSelectedItem();

        if (expenseType.isEmpty() || totalValue.isEmpty() || description.isEmpty() || selectedMember == null) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        TransactionsBetweenMembersHeadersModel expense = new TransactionsBetweenMembersHeadersModel();
        expense.setExpense_type(expenseType);
        expense.setExpense_total_value(Double.parseDouble(totalValue));
        expense.setExpense_description(description);
        expense.setPayed_by(selectedMember.getId());
        expense.setPayed_by_name(selectedMember.getName());

        TransactionsBetweenMembersHeadersDAO transactionsBetweenMembersHeadersDAO = new TransactionsBetweenMembersHeadersDAO(this);
        if(transactionsBetweenMembersHeadersDAO.Insert(expense) > 0){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddExpenseActivity.this, MainActivity.class));
            finish();
        };
    }
}
