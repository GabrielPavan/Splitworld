package com.example.splitworld;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splitworld.database.dao.UserDAO;
import com.example.splitworld.database.model.UserModel;
import com.example.splitworld.util.SharedKey;

public class NewAccountActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtMail;
    EditText edtPassword;
    EditText edtConfPassword;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        edtName = findViewById(R.id.editTextNameCreate);
        edtMail = findViewById(R.id.editTextEmailCreate);
        edtPassword = findViewById(R.id.editTextPassword);
        edtConfPassword = findViewById(R.id.editConfirmPassword);
        btnCreateAccount = findViewById(R.id.buttonCreateAccount);

        btnCreateAccount.setOnClickListener(v -> {
            if (validateAllFields()) {
                UserModel userModel = new UserModel();
                userModel.setName(edtName.getText().toString());
                userModel.setEmail(edtMail.getText().toString());
                if (edtPassword.getText().toString().equals(edtConfPassword.getText().toString())){
                    edtPassword.setBackgroundResource(R.drawable.edittext_border);
                    edtConfPassword.setBackgroundResource(R.drawable.edittext_border);
                    userModel.setPassword(edtPassword.getText().toString());
                } else {
                    edtPassword.setBackgroundResource(R.drawable.edittext_border_red);
                    edtConfPassword.setBackgroundResource(R.drawable.edittext_border_red);
                    return;
                }
                UserDAO userDAO = new UserDAO(NewAccountActivity.this);
                userDAO.Insert(userModel);
                showSuccessInsertDialog();
            } else {
                Toast.makeText(NewAccountActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });

        TextView lblHaveAccount = findViewById(R.id.labelHaveAccount);
        lblHaveAccount.setOnClickListener(v -> {
            startActivity(new Intent(NewAccountActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void showSuccessInsertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Success in creating the account")
                .setMessage("Do you want to save your login and proceed?")
                .setPositiveButton("OK", (dialog, which) -> {
                    Toast.makeText(NewAccountActivity.this, "Ok Performing login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewAccountActivity.this, MainActivity.class));
                    finish();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(NewAccountActivity.this, "Going back to the home screen", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewAccountActivity.this, LoginActivity.class));
                    finish();
                })
                .show();
    }
    private boolean validateAllFields() {
        EditText[] editTexts = {edtName, edtMail, edtPassword, edtConfPassword};
        for (EditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}