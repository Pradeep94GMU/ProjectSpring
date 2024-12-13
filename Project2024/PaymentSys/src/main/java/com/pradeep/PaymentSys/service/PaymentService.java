package com.pradeep.PaymentSys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {
    private final Map<String, PaymentFactory> paymentFactories;




    @Autowired
    public PaymentService(Map<String, PaymentFactory> paymentFactories) {
        this.paymentFactories = paymentFactories;
        for(Map.Entry<String, PaymentFactory> each: paymentFactories.entrySet()){
            System.out.println(each.getKey()+" "+each.getValue().toString());
        }
    }

    public void processPayment(String paymentType, double amount) {
        System.out.println(paymentType);
        System.out.println("hi1");
        PaymentFactory paymentFactory = paymentFactories.get(paymentType);
        System.out.println("hi2");
        if (paymentFactory != null) {
            System.out.println("hi3");
            Payment payment = paymentFactory.createPayment();
            payment.processPayment(amount);
        } else {
            throw new IllegalArgumentException("Payment type not supported: " + paymentType);
        }
    }
}
