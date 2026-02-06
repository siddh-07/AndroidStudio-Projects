package com.example.jav.Models;

public class OrderModel {
    int orderImage;
    String orderName, orderPrice, orderId;

    public OrderModel(int orderImage, String orderName, String orderPrice, String orderId) {
        this.orderImage = orderImage;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderId = orderId;
    }

    public OrderModel() {

    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}