package com.saskpolytech.CST000503804.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.saskpolytech.CST000503804.Adapter.ActivityItem;
import com.saskpolytech.CST000503804.Adapter.ActivityLogAdapter;
import com.saskpolytech.CST000503804.Database.DatabaseHelper;
import com.saskpolytech.CST000503804.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityLogFragment extends Fragment {

    private RecyclerView recyclerView;
    private ActivityLogAdapter adapter;
    private List<ActivityItem> activityList;
    private DatabaseHelper databaseHelper;
    private Button addActivity;
    private Button deleteActivity;
    private Button updateActivity;
    private SharedViewModel sharedViewModel;

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("Activity log", "in create view");

        activityList = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_activity_log, container, false);

        recyclerView = view.findViewById(R.id.activityRecyclerView);
        addActivity = view.findViewById(R.id.addActivityButton);
        deleteActivity = view.findViewById(R.id.deleteActivityButton);
        updateActivity = view.findViewById(R.id.updateActivityButton);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        databaseHelper = new DatabaseHelper(getActivity());

        activityList = databaseHelper.getAllActivities();
        Log.d("Total activities fetched", activityList.size()+"");

        adapter = new ActivityLogAdapter(activityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(adapter);


        double BMI = databaseHelper.getBmi();
        if (BMI != 0){
            addDailyActivitiesBasedOnBMI(BMI);
        }

        activityList.add(new ActivityItem("Strength training"," Engage in resistance exercises such as weightlifting, bodyweight exercises, or using resistance bands to build muscle mass."));
        activityList.add(new ActivityItem("Cardiovascular exercises"," Include activities like brisk walking, jogging, cycling, or swimming to improve cardiovascular health."));
        activityList.add(new ActivityItem("Yoga or Pilates"," Practice these activities to improve flexibility, posture, and overall body strength."));
        activityList.add(new ActivityItem("Sports"," Participate in sports activities such as soccer, basketball, or tennis to enhance muscle development and overall fitness."));

        activityList.add(new ActivityItem("Regular exercise routine"," Establish a consistent exercise routine that includes a combination of cardiovascular exercises, strength training, and flexibility exercises."));
        activityList.add(new ActivityItem("Aerobic activities"," Engage in activities like running, cycling, dancing, or aerobics classes to improve cardiovascular fitness."));
        activityList.add(new ActivityItem("Yoga or Pilates"," Practice these activities to maintain flexibility, improve posture, and enhance overall body strength."));
        activityList.add(new ActivityItem("Outdoor activities"," Take part in hiking, swimming, kayaking, or other outdoor activities to enjoy physical exercise while exploring nature."));

        activityList.add(new ActivityItem("Cardiovascular exercises"," Focus on high-intensity exercises like running, cycling, or interval training to burn calories and increase metabolism."));
        activityList.add(new ActivityItem("Strength training"," Incorporate strength training exercises to build lean muscle mass, which can help increase metabolism and burn more calories."));
        activityList.add(new ActivityItem("HIIT (High-Intensity Interval Training)"," Perform short bursts of intense exercise followed by active recovery periods to boost calorie burn and improve cardiovascular fitness."));
        activityList.add(new ActivityItem("Active lifestyle"," Increase overall physical activity by incorporating activities like walking or cycling instead of driving, taking the stairs instead of the elevator, and engaging in household chores or gardening."));


        adapter.notifyDataSetChanged();

        addActivity.setOnClickListener(event -> {
            showActivityInputDialogForAdd();
        });

        deleteActivity.setOnClickListener(event -> {
            showActivityInputDialogForDelete();
        });

        updateActivity.setOnClickListener(event -> {
            showActivityInputDialogForUpdate();
        });

        return view;
    }

    private void addActivity(String name, String details) {
        ActivityItem activityItem = new ActivityItem(name, details);
        databaseHelper.addActivity(activityItem.getName(),activityItem.getDetails());
        refreshActivity();
    }

    private void deleteActivity(ActivityItem activityItem) {
        databaseHelper.deleteActivity(activityItem.getName());
        refreshActivity();
    }

    private void updateActivity(String oldName ,String newName, String newDetails) {
        databaseHelper.updateActivity(newName,newDetails);
        refreshActivity();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refreshActivity() {
        activityList.clear();
        activityList.addAll(databaseHelper.getAllActivities());
        adapter.notifyDataSetChanged();
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void showActivityInputDialogForAdd() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_input_layout, null);
        dialogBuilder.setView(dialogView);

        EditText activityName = dialogView.findViewById(R.id.etActivityName);
        EditText activityDetails = dialogView.findViewById(R.id.etActivityDetails);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String acName = activityName.getText().toString().trim();
                String acDetails = activityDetails.getText().toString().trim();

                addActivity(acName, acDetails);
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void showActivityInputDialogForDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_input_layout, null);
        dialogBuilder.setView(dialogView);

        EditText activityName = dialogView.findViewById(R.id.etActivityName);
        EditText activityDetails = dialogView.findViewById(R.id.etActivityDetails);

        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String acName = activityName.getText().toString().trim();
                String acDetails = activityDetails.getText().toString().trim();

                deleteActivity(new ActivityItem(acName,acDetails));
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void showActivityInputDialogForUpdate() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.update_layout, null);
        dialogBuilder.setView(dialogView);

        EditText oldActivityName = dialogView.findViewById(R.id.etChangedName);
        EditText activityName = dialogView.findViewById(R.id.etUpdateName);
        EditText activityDetails = dialogView.findViewById(R.id.etUpdateDetails);

        dialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String acOld = oldActivityName.getText().toString().trim();
                String acName = activityName.getText().toString().trim();
                String acDetails = activityDetails.getText().toString().trim();

                updateActivity(acOld,acName,acDetails);
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addDailyActivitiesBasedOnBMI(double bmi) {

        if (bmi < 18.5) {
        activityList.add(new ActivityItem("Strength training"," Engage in resistance exercises such as weightlifting, bodyweight exercises, or using resistance bands to build muscle mass."));
        activityList.add(new ActivityItem("Cardiovascular exercises"," Include activities like brisk walking, jogging, cycling, or swimming to improve cardiovascular health."));
        activityList.add(new ActivityItem("Yoga or Pilates"," Practice these activities to improve flexibility, posture, and overall body strength."));
        activityList.add(new ActivityItem("Sports"," Participate in sports activities such as soccer, basketball, or tennis to enhance muscle development and overall fitness."));
            for (ActivityItem activityItem:activityList) {
                addActivity(activityItem.getName(),activityItem.getDetails());
            }
        refreshActivity();
        } else if (bmi >= 18.5 && bmi < 25) {
        activityList.add(new ActivityItem("Regular exercise routine"," Establish a consistent exercise routine that includes a combination of cardiovascular exercises, strength training, and flexibility exercises."));
        activityList.add(new ActivityItem("Aerobic activities"," Engage in activities like running, cycling, dancing, or aerobics classes to improve cardiovascular fitness."));
        activityList.add(new ActivityItem("Yoga or Pilates"," Practice these activities to maintain flexibility, improve posture, and enhance overall body strength."));
        activityList.add(new ActivityItem("Outdoor activities"," Take part in hiking, swimming, kayaking, or other outdoor activities to enjoy physical exercise while exploring nature."));
            for (ActivityItem activityItem:activityList) {
                addActivity(activityItem.getName(),activityItem.getDetails());
            }
        refreshActivity();
        } else {
        activityList.add(new ActivityItem("Cardiovascular exercises"," Focus on high-intensity exercises like running, cycling, or interval training to burn calories and increase metabolism."));
        activityList.add(new ActivityItem("Strength training"," Incorporate strength training exercises to build lean muscle mass, which can help increase metabolism and burn more calories."));
        activityList.add(new ActivityItem("HIIT (High-Intensity Interval Training)"," Perform short bursts of intense exercise followed by active recovery periods to boost calorie burn and improve cardiovascular fitness."));
        activityList.add(new ActivityItem("Active lifestyle"," Increase overall physical activity by incorporating activities like walking or cycling instead of driving, taking the stairs instead of the elevator, and engaging in household chores or gardening."));
            for (ActivityItem activityItem:activityList) {
                addActivity(activityItem.getName(),activityItem.getDetails());
            }
        refreshActivity();
        }

        adapter.notifyDataSetChanged();
    }
}
