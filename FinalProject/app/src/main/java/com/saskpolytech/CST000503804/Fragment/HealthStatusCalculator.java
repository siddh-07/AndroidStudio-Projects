package com.saskpolytech.CST000503804.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.saskpolytech.CST000503804.Database.DatabaseHelper;
import com.saskpolytech.CST000503804.R;


public class HealthStatusCalculator extends Fragment {
    private EditText etName, etAge, etHeight, etWeight;
    private Button btnCalculate;
    private TextView tvResult;
    private DatabaseHelper databaseHelper;

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.status_layout, container, false);

        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etHeight = view.findViewById(R.id.etHeight);
        etWeight = view.findViewById(R.id.etWeight);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        tvResult = view.findViewById(R.id.tvResult);
        databaseHelper = new DatabaseHelper(getActivity());

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void calculateBMI() {

        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        double height = Double.parseDouble(etHeight.getText().toString());
        double weight = Double.parseDouble(etWeight.getText().toString());

        double bmi = weight / (height * height);

        databaseHelper.addBmi(bmi);

        String s;
        if (bmi < 18.5) {
            s = "UNDER WEIGHT";
        } else if (bmi >= 18.5 && bmi < 25) {
            s = "NORMAL WEIGHT";
        } else if (bmi >= 25 && bmi < 30) {
            s = "OVER WEIGHT";
        } else {
            s = "OBESE";
        }

        String result = "Name: " + name + "\nAge: " + age + "\nBMI: " + s;

        tvResult.setText(result);
    }
}
