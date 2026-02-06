package com.example.jav.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jav.BurgerDetailActivity;
import com.example.jav.Models.HotDrinksModel;
import com.example.jav.R;

import java.util.ArrayList;

public class HotDrinksAdapter extends RecyclerView.Adapter<HotDrinksAdapter.viewholder> {
    @NonNull
    ArrayList<HotDrinksModel> blist;
    Context context;

    public HotDrinksAdapter(ArrayList<HotDrinksModel> blist, Context context) {
        this.blist = blist;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_hot_drinks, parent, false);
        return new HotDrinksAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final HotDrinksModel bmodel = blist.get(position);
        holder.bImage.setImageResource(bmodel.getImage());
        holder.bName.setText(bmodel.getbName());
        holder.bPrice.setText(bmodel.getbPrice());
        holder.bCals.setText(bmodel.getbCals());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BurgerDetailActivity.class);
                intent.putExtra("image", bmodel.getImage());
                intent.putExtra("name", bmodel.getbName());
                intent.putExtra("price", bmodel.getbPrice());
                intent.putExtra("cals", bmodel.getbCals());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return blist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView bImage;
        TextView bName, bPrice, bCals;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            bImage = itemView.findViewById(R.id.orderImg);
            bName = itemView.findViewById(R.id.orderName);
            bPrice = itemView.findViewById(R.id.orderPrice);
            bCals = itemView.findViewById(R.id.bCals);

        }
    }
}