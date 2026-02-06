package com.saskpolytech.CST000503804.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saskpolytech.CST000503804.R;

import java.util.List;

public class ActivityLogAdapter extends RecyclerView.Adapter<ActivityLogAdapter.ActivityViewHolder> {

    private List<ActivityItem> activityList;

    public ActivityLogAdapter(List<ActivityItem> activityList) {
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        ActivityItem item = activityList.get(position);
        holder.activityName.setText(item.getName());
        holder.activityDetails.setText(item.getDetails());
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView activityName, activityDetails;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            activityName = itemView.findViewById(R.id.activityName);
            activityDetails = itemView.findViewById(R.id.activityDetails);
        }
    }
}
