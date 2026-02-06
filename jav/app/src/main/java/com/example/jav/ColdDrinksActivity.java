package com.example.jav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;

import com.example.jav.Adapters.BurgerAdapter;
import com.example.jav.Adapters.ColdDrinksAdapter;
import com.example.jav.Models.BurgerModel;
import com.example.jav.Models.ColdDrinksModel;
import com.example.jav.databinding.ActivityColdDrinksBinding;
import com.example.jav.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class ColdDrinksActivity extends AppCompatActivity {

    ActivityColdDrinksBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_cold_drinks);
        binding = ActivityColdDrinksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<ColdDrinksModel> list = new ArrayList<>();

        list.add(new ColdDrinksModel(R.drawable.bigmac, "Big Mac", "6.19", "560 Cals"));
        list.add(new ColdDrinksModel(R.drawable.dmac, "Double Big Mac", "7.39", "730 Cals"));
        list.add(new ColdDrinksModel(R.drawable.quater, "Quater Pounder", "6.79", "530 Cals"));
        list.add(new ColdDrinksModel(R.drawable.dquater, "Double Quater Pounder", "8.59", "750 Cals"));
        list.add(new ColdDrinksModel(R.drawable.cheese, "Cheese Burger ", "2.49", "290 Cals"));
        list.add(new ColdDrinksModel(R.drawable.dcheese, "Double Cheese Burger", "3.69", "420 Cals"));
        list.add(new ColdDrinksModel(R.drawable.mcdouble, "McDouble", "3.19", "380 Cals"));
        list.add(new ColdDrinksModel(R.drawable.jrchicken, "Junior Chicken", "3.19", "320 Cals"));
        list.add(new ColdDrinksModel(R.drawable.mccrispy, "McCrispy", "8.19", "520 Cals"));
        list.add(new ColdDrinksModel(R.drawable.mcchicken, "McChicken", "5.59", "470 Cals"));

        ColdDrinksAdapter adapter = new ColdDrinksAdapter(list, this);
        binding.recyclerviewCold.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerviewCold.setLayoutManager(layoutManager);

    }
    }
