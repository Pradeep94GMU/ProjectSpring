package com.pradeep.SagaOrch.Controller;

public class Inventory {

    private String productName;
    private int stock;

    public Inventory() {
    }

    public Inventory(String productName, int stock) {
        this.productName = productName;
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
