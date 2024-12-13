package com.pradeep.PaymentSys.service.serviceImplemetation;

import com.pradeep.PaymentSys.service.Payment;
import org.springframework.stereotype.Service;

@Service
public class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}
