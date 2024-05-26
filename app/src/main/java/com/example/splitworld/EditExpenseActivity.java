package com.example.splitworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splitworld.database.dao.MemberDAO;
import com.example.splitworld.database.dao.TransactionsBetweenMembersHeadersDAO;
import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;

import java.util.List;

public class EditExpenseActivity extends AppCompatActivity {
    private Spinner spinnerExpenseType;
    private EditText editTextTotalValue;
    private EditText editTextDescription;
    private Spinner spinnerMembers;
    private Button btnAddExpense;
    private Button btnCancelExpense;
    TransactionsBetweenMembersHeadersModel transactionsBetweenMembersHeadersModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        spinnerExpenseType = findViewById(R.id.spinnerExpenseType);
        editTextTotalValue = findViewById(R.id.editTextTotalValue);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerMembers = findViewById(R.id.spinnerMember);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnCancelExpense = findViewById(R.id.btnCancelExpense);

        Intent intent = getIntent();
        int id_expense = 0;
        if(intent != null){
            id_expense = intent.getIntExtra("id_expense", -1);
            if (id_expense == -1)
                finish();
        }
        loadExpense(id_expense);
        loadMembers();
        loadExpenseTypes();

        btnCancelExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditExpenseActivity.this, MainActivity.class));
                finish();
            }
        });
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExpense();
            }
        });
    }

    private void updateExpense() {
        String expenseType = spinnerExpenseType.getSelectedItem().toString();
        String totalValue = editTextTotalValue.getText().toString();
        String description = editTextDescription.getText().toString();
        MemberModel selectedMember = (MemberModel) spinnerMembers.getSelectedItem();

        if (expenseType.isEmpty() || totalValue.isEmpty() || description.isEmpty() || selectedMember == null) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        TransactionsBetweenMembersHeadersModel expense = new TransactionsBetweenMembersHeadersModel();
        expense.setId(transactionsBetweenMembersHeadersModel.getId());
        expense.setExpense_type(expenseType);
        expense.setExpense_total_value(Double.parseDouble(totalValue));
        expense.setExpense_description(description);
        expense.setPayed_by(selectedMember.getId());
        expense.setPayed_by_name(selectedMember.getName());

        TransactionsBetweenMembersHeadersDAO transactionsBetweenMembersHeadersDAO = new TransactionsBetweenMembersHeadersDAO(this);
        if(transactionsBetweenMembersHeadersDAO.Update(expense) > 0){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditExpenseActivity.this, MainActivity.class));
            finish();
        };
    }

    private void loadExpense(int id_expense) {
        TransactionsBetweenMembersHeadersDAO transactionsBetweenMembersHeadersDAO = new TransactionsBetweenMembersHeadersDAO(this);
        transactionsBetweenMembersHeadersModel = transactionsBetweenMembersHeadersDAO.findById(id_expense);
        editTextTotalValue.setText(String.valueOf(transactionsBetweenMembersHeadersModel.getExpense_total_value()));
        editTextDescription.setText(transactionsBetweenMembersHeadersModel.getExpense_description());
    }
    private void loadMembers() {
        MemberDAO memberDAO = new MemberDAO(this);
        List<MemberModel> members = memberDAO.findAll();
        ArrayAdapter<MemberModel> adapter = new ArrayAdapter<>(this, R.layout.simple_spinner_dropdown_item, members);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        int position = adapter.getPosition(memberDAO.findById(transactionsBetweenMembersHeadersModel.getPayed_by()));
        spinnerMembers.setAdapter(adapter);
        spinnerMembers.setSelection(position);
    }
    private void loadExpenseTypes() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.expense_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int position = adapter.getPosition(transactionsBetweenMembersHeadersModel.getExpense_type());
        spinnerExpenseType.setAdapter(adapter);
        spinnerExpenseType.setSelection(position);
    }
}
