package com.saskpolytech.CST000503804.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saskpolytech.CST000503804.R;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalViewHolder> {

    private List<GoalItem> goalList;

    public GoalsAdapter(List<GoalItem> goalList) {
        this.goalList = goalList;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goal, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        GoalItem item = goalList.get(position);
        holder.goalName.setText(item.getName());
        holder.goalDetails.setText(item.getDetails());
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    public class GoalViewHolder extends RecyclerView.ViewHolder {
        TextView goalName, goalDetails;

        public GoalViewHolder(View itemView) {
            super(itemView);
            goalName = itemView.findViewById(R.id.goalName);
            goalDetails = itemView.findViewById(R.id.goalDetails);
        }
    }
}
