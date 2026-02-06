package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText id,pass;
    Button signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id=findViewById(R.id.id1);
        pass=findViewById(R.id.pass1);
        signin=findViewById(R.id.signin1);
        DB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String id1= id.getText().toString();
                String password=pass.getText().toString();

                if(TextUtils.isEmpty(id1) || TextUtils.isEmpty(password))
                    Toast.makeText(LoginActivity.this, "All fields required",Toast.LENGTH_SHORT).show();
                else{
                            Boolean checkidpass = DB.checkidpasswrd(id1, password);
                            if (checkidpass) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                }
            }
        });
    }
}