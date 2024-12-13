package com.pradeep.PaymentSys.service.factory;

import com.pradeep.PaymentSys.service.Payment;
import com.pradeep.PaymentSys.service.PaymentFactory;
import com.pradeep.PaymentSys.service.serviceImplemetation.CreditCardPayment;
import org.springframework.stereotype.Component;

@Component("creditCard")
public class CreditCardFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
