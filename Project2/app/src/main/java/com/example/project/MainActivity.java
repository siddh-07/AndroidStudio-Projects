package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,clas,ids,pass,cpass;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        clas=findViewById(R.id.clas);
        ids=findViewById(R.id.ids);
        pass=findViewById(R.id.pass);
        cpass=findViewById(R.id.cpass);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String id=ids.getText().toString();
                String cla= clas.getText().toString();
                String names=name.getText().toString();
                String password=pass.getText().toString();
                String cpassword = cpass.getText().toString();

                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(cla) || TextUtils.isEmpty(names) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cpassword))
                    Toast.makeText(MainActivity.this, "All fields required",Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(cpassword)){
                        Boolean checkuid=DB.checkid(id);
                        if(!checkuid) {
                            Boolean insert = DB.insertData(id, String.valueOf(password));
                            if (insert) {
                                Toast.makeText(MainActivity.this, "Registered Complete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}