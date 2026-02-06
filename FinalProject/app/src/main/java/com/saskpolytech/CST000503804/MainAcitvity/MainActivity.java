package com.saskpolytech.CST000503804.MainAcitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.saskpolytech.CST000503804.R;

public class MainActivity extends AppCompatActivity {

    Button login ;
    Button register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginMain);
        register = findViewById(R.id.regsterMain);

        login.setOnClickListener(view -> {
            Intent intent1 = new Intent();
            intent1.setClass(this,LoginActivity.class);
            startActivity(intent1);
        });

        register.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            intent1.setClass(this, RegisterActivity.class);
            startActivity(intent1);
        });
    }
}