package com.example.jav;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jav.Adapters.DBHelper;
import com.example.jav.databinding.ActivityBurgerDetailBinding;

public class BurgerDetailActivity extends AppCompatActivity {
    ActivityBurgerDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBurgerDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int image = getIntent().getIntExtra("image", 0);
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String cals = getIntent().getStringExtra("cals");


        binding.detailimg.setImageResource(image);
        binding.name.setText(name);
        binding.price.setText(price);
        binding.cals.setText(cals);


        DBHelper helper = new DBHelper(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted  = helper.insertOrder(
                       binding.name.getText().toString(),
                        price,
                        image,
                        cals

                );
               if(isInserted){
                   Toast.makeText(BurgerDetailActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(BurgerDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
               }
            }
        });
Button bt = findViewById(R.id.button4);
bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        open();
    }
});
    }
    public void open(){
        Intent intent = new Intent(BurgerDetailActivity.this, OrdersActivity.class);
        startActivity(intent);
    }
}
