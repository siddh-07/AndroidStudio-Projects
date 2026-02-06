package com.example.pro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro.Models.BurgerModel;
import com.example.pro.R;

import java.util.ArrayList;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.viewholder>{

        ArrayList<BurgerModel> blist;
        Context context;

    public BurgerAdapter(ArrayList<BurgerModel> blist, Context context) {
        this.blist = blist;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.burgers, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
            final BurgerModel bmodel = blist.get(position);
            holder.bImage.setImageResource(bmodel.getImage());
            holder.bName.setText(bmodel.getbName());
            holder.bPrice.setText(bmodel.getbPrice());
            holder.bCals.setText(bmodel.getbCals());
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

            bImage = itemView.findViewById(R.id.bIcon);
            bName = itemView.findViewById(R.id.bName);
            bPrice = itemView.findViewById(R.id.bPrice);
            bCals = itemView.findViewById(R.id.bCals);

        }
    }
}
