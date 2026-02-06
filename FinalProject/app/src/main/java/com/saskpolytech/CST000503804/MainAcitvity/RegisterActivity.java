package com.saskpolytech.CST000503804.MainAcitvity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saskpolytech.CST000503804.R;
import com.saskpolytech.CST000503804.Database.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private EditText etEmail, etPassword;
    private Button btnRegister;
    private Button btnLogin;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.registerLogin);


        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (isValidEmail(email) && email.length() > 10) {

                DatabaseHelper databaseHelper = new DatabaseHelper(this);
                long userId = databaseHelper.addUser(email, password);

                if (userId != -1) {

                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
                }

            } else if (password.length() < 6) {
                Toast.makeText(getApplicationContext(), "Password should contain 6 or more words or number.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Email ID : " + email + " is not valid", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
