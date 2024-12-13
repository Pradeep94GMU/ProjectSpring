package com.pradeep.SagaOrch.Controller;

public class Payment {

    private Long orderId;
    private double amount;

    public Payment() {
    }

    public Payment(Long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
