package com.example.jav;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jav.Adapters.BurgerAdapter;
import com.example.jav.Models.BurgerModel;
import com.example.jav.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
MenuItem it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar m = (Toolbar) findViewById(R.id.toolbar);
        m.setTitle("Hungerr");
        setSupportActionBar(m);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<BurgerModel> list = new ArrayList<>();

        list.add(new BurgerModel(R.drawable.bigmac, "Big Mac", "6.19", "560 Cals"));
        list.add(new BurgerModel(R.drawable.dmac, "Double Big Mac", "7.39", "730 Cals"));
        list.add(new BurgerModel(R.drawable.quater, "Quater Pounder", "6.79", "530 Cals"));
        list.add(new BurgerModel(R.drawable.dquater, "Double Quater Pounder", "8.59", "750 Cals"));
        list.add(new BurgerModel(R.drawable.cheese, "Cheese Burger ", "2.49", "290 Cals"));
        list.add(new BurgerModel(R.drawable.dcheese, "Double Cheese Burger", "3.69", "420 Cals"));
        list.add(new BurgerModel(R.drawable.mcdouble, "McDouble", "3.19", "380 Cals"));
        list.add(new BurgerModel(R.drawable.jrchicken, "Junior Chicken", "3.19", "320 Cals"));
        list.add(new BurgerModel(R.drawable.mccrispy, "McCrispy", "8.19", "520 Cals"));
        list.add(new BurgerModel(R.drawable.mcchicken, "McChicken", "5.59", "470 Cals"));

        BurgerAdapter adapter = new BurgerAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.recyclerview.setLayoutManager(layoutManager);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        it = findViewById(R.id.newGame);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.newGame) {
            Intent intent = new Intent();
            intent.setClass(this, OrdersActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}