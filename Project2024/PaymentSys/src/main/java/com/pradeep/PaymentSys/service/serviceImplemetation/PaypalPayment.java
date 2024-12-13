package com.pradeep.PaymentSys.service.serviceImplemetation;

import com.pradeep.PaymentSys.service.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaypalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}