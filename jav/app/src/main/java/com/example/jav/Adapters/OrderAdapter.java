package com.example.jav.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jav.Models.OrderModel;
import com.example.jav.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder>{

    ArrayList<OrderModel> list;
    Context context;

    public  OrderAdapter(ArrayList<OrderModel> list, Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orders, parent, false);
        return new OrderAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final OrderModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.ordername.setText(model.getOrderName());

        holder.orderid.setText(model.getOrderId());
        holder.orderprice.setText(model.getOrderPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView orderImage;
        TextView ordername,orderprice, orderid;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
        orderImage = itemView.findViewById(R.id.orderImg);
            ordername = itemView.findViewById(R.id.orderName);
            orderprice = itemView.findViewById(R.id.orderPrice);
            orderid = itemView.findViewById(R.id.orderID);
        }
    }
}
