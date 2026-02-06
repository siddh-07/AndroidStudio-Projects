package com.example.jav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.jav.Adapters.DBHelper;
import com.example.jav.Adapters.OrderAdapter;
import com.example.jav.Models.OrderModel;
import com.example.jav.databinding.ActivityMainBinding;
import com.example.jav.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    ActivityOrdersBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DBHelper helper = new DBHelper(this);
        ArrayList<OrderModel> list= helper.getOrder();
      //  list.add(new OrderModel(R.drawable.bigmac,"mc","78","8"));


        OrderAdapter adapter = new OrderAdapter(list, this);
        binding.recyclerviewOrders.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerviewOrders.setLayoutManager(layoutManager);

    }
}