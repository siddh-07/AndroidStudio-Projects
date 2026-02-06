package com.saskpolytech.CST000503804.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.saskpolytech.CST000503804.Adapter.GoalItem;
import com.saskpolytech.CST000503804.Adapter.GoalsAdapter;
import com.saskpolytech.CST000503804.Database.DatabaseHelper;
import com.saskpolytech.CST000503804.R;

import java.util.ArrayList;
import java.util.List;

public class GoalsFragment extends Fragment {

    private RecyclerView recyclerView;
    private GoalsAdapter adapter;
    private List<GoalItem> goalList;
    private DatabaseHelper databaseHelper;
    private Button addGoal;
    private Button deleteGoal;
    private Button updateGoal;

    private SharedViewModel sharedViewModel;

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        recyclerView = view.findViewById(R.id.goalRecyclerView);
        addGoal = view.findViewById(R.id.addGoalButton);
        deleteGoal = view.findViewById(R.id.deleteGoalButton);
        updateGoal = view.findViewById(R.id.updateGoalButton);
        goalList = new ArrayList<>();

        databaseHelper = new DatabaseHelper(getActivity());

        goalList = databaseHelper.getAllGoals();

        adapter = new GoalsAdapter(goalList);

        adapter = new GoalsAdapter(goalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        goalList.add(new GoalItem("Gain weight gradually","Increase muscle mass"));
        goalList.add(new GoalItem("Increase Caloric Intake"," Consume more calories than your body burns. Include calorie-dense foods in your meals and snacks, such as nuts, avocados, nut butter, and dried fruits."));
        goalList.add(new GoalItem("Keep your self Hydrated","Drink enough water throughout the day to stay hydrated. Avoid filling up on calorie-free beverages, as they may reduce your appetite."));
        goalList.add(new GoalItem("Track Your Progress"," Monitor your weight regularly and keep a record of your food intake. This can help you identify areas for improvement and track your progress over time."));
        goalList.add(new GoalItem("Portion Control"," Be mindful of portion sizes and avoid overeating. Use smaller plates, bowls, and utensils to help control portion sizes."));
        goalList.add(new GoalItem("Balanced Diet"," Follow a balanced and nutritious diet that includes a variety of foods from all food groups. Include lean proteins, whole grains, fruits, vegetables, healthy fats, and low-fat dairy products."));
        goalList.add(new GoalItem("Physical Activity"," Engage in regular physical activity to support weight maintenance. Aim for at least 150 minutes of moderate-intensity aerobic activity or 75 minutes of vigorous-intensity activity per week, along with strength training exercises."));
        goalList.add(new GoalItem("Manage Stress"," Find healthy ways to manage stress, as stress can sometimes lead to emotional eating or disruptions in eating patterns. Engage in relaxation techniques, physical activity, or hobbies that help you unwind."));
        goalList.add(new GoalItem("Seek Support","Consider joining a weight loss program, seeking support from a healthcare professional, or joining a support group to receive guidance, accountability, and encouragement on your weight loss journey."));
        goalList.add(new GoalItem("Set Realistic Goals"," Set achievable and realistic weight loss goals. Aim for gradual and steady weight loss of 1-2 pounds per week, which is considered a healthy and sustainable rate."));
        goalList.add(new GoalItem("Stay Consistent"," Consistency is key. Stick to your healthy eating and exercise plan, even on days when you may feel less motivated. Remember that sustainable weight loss takes time and effort."));
        goalList.add(new GoalItem("Consult a Healthcare Professional"," If you have underlying health conditions or if your obesity is severe, it's important to consult with a healthcare professional or a registered dietitian who can provide personalized guidance and support."));

        adapter.notifyDataSetChanged();

        addGoal.setOnClickListener(event -> {
            showGoalInputDialogForAdd();
        });

        deleteGoal.setOnClickListener( event -> {
            showGoalInputDialogForDelete();
        });

        updateGoal.setOnClickListener( event -> {
            showGoalInputDialogForUpdate();
        });

        return view;
    }

    private void addGoal(String name, String details) {
        GoalItem goalItem = new GoalItem(name, details);
        databaseHelper.addGoal(goalItem);
        refreshGoals();
    }

    private void deleteGoal(GoalItem goalItem) {
        databaseHelper.deleteGoal(goalItem.getName());
        refreshGoals();
    }

    private void updateGoal(String oldName, String newName, String newDetails) {
        databaseHelper.updateGoal(new GoalItem(newName,newDetails));
        refreshGoals();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refreshGoals() {
        goalList.clear();
        goalList.addAll(databaseHelper.getAllGoals());
        adapter.notifyDataSetChanged();
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void showGoalInputDialogForAdd() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.goal_input_layout, null);
        dialogBuilder.setView(dialogView);

         EditText etGoalName = dialogView.findViewById(R.id.etGoalName);
         EditText etGoalDetails = dialogView.findViewById(R.id.etGoalDetails);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String goalName = etGoalName.getText().toString().trim();
                String goalDetails = etGoalDetails.getText().toString().trim();

                addGoal(goalName, goalDetails);
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
    private void showGoalInputDialogForDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.goal_input_layout, null);
        dialogBuilder.setView(dialogView);

        EditText etGoalName = dialogView.findViewById(R.id.etGoalName);
        EditText etGoalDetails = dialogView.findViewById(R.id.etGoalDetails);

        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String goalName = etGoalName.getText().toString().trim();
                String goalDetails = etGoalDetails.getText().toString().trim();

                deleteGoal(new GoalItem(goalName,goalDetails));
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
    private void showGoalInputDialogForUpdate() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.update_layout, null);
        dialogBuilder.setView(dialogView);

        EditText oldGoalName = dialogView.findViewById(R.id.etChangedName);
        EditText goalName = dialogView.findViewById(R.id.etUpdateName);
        EditText goalDetails = dialogView.findViewById(R.id.etUpdateDetails);

        dialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String gOld = oldGoalName.getText().toString().trim();
                String gName = goalName.getText().toString().trim();
                String gDetails = goalDetails.getText().toString().trim();

                updateGoal(gOld,gName,gDetails);
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

    private void displayGoalsBasedOnBMI(double bmi) {
        goalList = new ArrayList<>();

        if (bmi < 18.5) {
            goalList.add(new GoalItem("Gain weight gradually","Increase muscle mass"));
            goalList.add(new GoalItem("Increase Caloric Intake"," Consume more calories than your body burns. Include calorie-dense foods in your meals and snacks, such as nuts, avocados, nut butter, and dried fruits."));
            goalList.add(new GoalItem("Keep your self Hydrated","Drink enough water throughout the day to stay hydrated. Avoid filling up on calorie-free beverages, as they may reduce your appetite."));
            goalList.add(new GoalItem("Track Your Progress"," Monitor your weight regularly and keep a record of your food intake. This can help you identify areas for improvement and track your progress over time."));
        } else if (bmi >= 18.5 && bmi < 25) {
            goalList.add(new GoalItem("Portion Control"," Be mindful of portion sizes and avoid overeating. Use smaller plates, bowls, and utensils to help control portion sizes."));
            goalList.add(new GoalItem("Balanced Diet"," Follow a balanced and nutritious diet that includes a variety of foods from all food groups. Include lean proteins, whole grains, fruits, vegetables, healthy fats, and low-fat dairy products."));
            goalList.add(new GoalItem("Physical Activity"," Engage in regular physical activity to support weight maintenance. Aim for at least 150 minutes of moderate-intensity aerobic activity or 75 minutes of vigorous-intensity activity per week, along with strength training exercises."));
            goalList.add(new GoalItem("Manage Stress"," Find healthy ways to manage stress, as stress can sometimes lead to emotional eating or disruptions in eating patterns. Engage in relaxation techniques, physical activity, or hobbies that help you unwind."));
        } else {
            goalList.add(new GoalItem("Seek Support","Consider joining a weight loss program, seeking support from a healthcare professional, or joining a support group to receive guidance, accountability, and encouragement on your weight loss journey."));
            goalList.add(new GoalItem("Set Realistic Goals"," Set achievable and realistic weight loss goals. Aim for gradual and steady weight loss of 1-2 pounds per week, which is considered a healthy and sustainable rate."));
            goalList.add(new GoalItem("Stay Consistent"," Consistency is key. Stick to your healthy eating and exercise plan, even on days when you may feel less motivated. Remember that sustainable weight loss takes time and effort."));
            goalList.add(new GoalItem("Consult a Healthcare Professional"," If you have underlying health conditions or if your obesity is severe, it's important to consult with a healthcare professional or a registered dietitian who can provide personalized guidance and support."));
        }
        refreshGoals();
    }
}
