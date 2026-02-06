package com.example.pro.Models;

public class BurgerModel {
    int image;
    String bName, bPrice, bCals;

    public BurgerModel(int image, String bName, String bPrice, String bCals) {
        this.image = image;
        this.bName = bName;
        this.bPrice = bPrice;
        this.bCals = bCals;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbPrice() {
        return bPrice;
    }

    public void setbPrice(String bPrice) {
        this.bPrice = bPrice;
    }

    public String getbCals() {
        return bCals;
    }

    public void setbCals(String bCals) {
        this.bCals = bCals;
    }
}
