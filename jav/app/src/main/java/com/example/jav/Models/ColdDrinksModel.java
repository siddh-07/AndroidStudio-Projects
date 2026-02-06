package com.example.jav.Models;

public class ColdDrinksModel {
    int image;
    String name, price, cals;

    public ColdDrinksModel(int image, String bName, String bPrice, String bCals) {
        this.image = image;
        this.name = bName;
        this.price = bPrice;
        this.cals = bCals;
    }
    public int getImage() {
        return image;
    }

    public void setColdImage(int image) {
        this.image = image;
    }

    public String getbName() {
        return name;
    }

    public void setbName(String bName) {
        this.name = bName;
    }

    public String getbPrice() {
        return price;
    }

    public void setbPrice(String bPrice) {
        this.price = bPrice;
    }

    public String getbCals() {
        return cals;
    }

    public void setbCals(String bCals) {
        this.cals = bCals;
    }

}
