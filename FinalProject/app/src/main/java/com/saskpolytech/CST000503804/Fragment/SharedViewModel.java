package com.saskpolytech.CST000503804.Fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Double> bmiLiveData = new MutableLiveData<>();

    public void setBMI(double bmi) {
        bmiLiveData.setValue(bmi);
    }

    public LiveData<Double> getBMI() {
        return bmiLiveData;
    }
}

