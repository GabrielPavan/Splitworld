package com.example.splitworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splitworld.database.dao.UserDAO;
import com.example.splitworld.util.SharedKey;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView lblCreateAccount = findViewById(R.id.labelCreateAccount);
        lblCreateAccount.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, NewAccountActivity.class));
            finish();
        });

        Switch swtSaveLogin = findViewById(R.id.swt_save_button);
        EditText editTextMail = findViewById(R.id.editTextEmail);
        EditText editTextPass = findViewById(R.id.editTextPassword);
        Button btnLogin = findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(v -> {
            UserDAO userDAO = new UserDAO(this);
            if(userDAO.TryLogin(editTextMail.getText().toString(), editTextPass.getText().toString())){
                if(swtSaveLogin.isChecked()){
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString(SharedKey.KEY_MAIL, editTextMail.getText().toString());
                    edit.putString(SharedKey.KEY_PASSWORD, editTextPass.getText().toString());
                    edit.putBoolean(SharedKey.KEY_SWITCH, swtSaveLogin.isChecked());
                    edit.apply();
                }
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                return;
            }
            Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editTextMail.setText(preferences.getString(SharedKey.KEY_MAIL,""));
        editTextPass.setText(preferences.getString(SharedKey.KEY_PASSWORD,""));
        swtSaveLogin.setChecked(preferences.getBoolean(SharedKey.KEY_SWITCH, true));
    }
}