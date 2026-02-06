package com.saskpolytech.CST000503804.Adapter;

public class GoalItem {

    private String name;
    private String details;

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public GoalItem(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }
}
