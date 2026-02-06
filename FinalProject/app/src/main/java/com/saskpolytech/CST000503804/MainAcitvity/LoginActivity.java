package com.saskpolytech.CST000503804.MainAcitvity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saskpolytech.CST000503804.R;
import com.saskpolytech.CST000503804.Database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private Button btnBack;
    private DatabaseHelper databaseHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnBack = findViewById(R.id.back);

        databaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            boolean isValidUser = databaseHelper.checkUser(email, password);

            if (isValidUser) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(this, FitnessTrackingActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
