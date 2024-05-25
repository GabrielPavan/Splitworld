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

import com.example.splitworld.util.SharedKey;

public class MainActivity extends AppCompatActivity  {
    private TextView textViewDestiny;
    private ImageView iconMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDestiny = findViewById(R.id.textDestiny);
        iconMembers = findViewById(R.id.iconBotton1);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        textViewDestiny.setText(preferences.getString(SharedKey.KEY_DESTINY, ""));
        textViewDestiny.setOnClickListener(v -> {
            showChangeDestinyDialog();
        });
        if(textViewDestiny.getText().toString().isEmpty()){
            textViewDestiny.performClick();
        }
        iconMembers.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MemberListActivity.class));
        });
    }

    private void showChangeDestinyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set your destiny: ");

        final EditText editText = new EditText(this);
        editText.setText("");
        builder.setView(editText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newText = editText.getText().toString();
                if (!newText.isEmpty()){
                    textViewDestiny.setText(newText);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString(SharedKey.KEY_DESTINY, newText);
                    edit.apply();
                } else {
                    Toast.makeText(MainActivity.this, "Destiny not be empty", Toast.LENGTH_SHORT).show();
                    showChangeDestinyDialog();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Destiny not be empty", Toast.LENGTH_SHORT).show();
                showChangeDestinyDialog();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}