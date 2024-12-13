package com.pradeep.PaymentSys.service.factory;

import com.pradeep.PaymentSys.service.Payment;
import com.pradeep.PaymentSys.service.PaymentFactory;
import com.pradeep.PaymentSys.service.serviceImplemetation.PaypalPayment;
import org.springframework.stereotype.Component;

@Component("paypal")
public class PaypalFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        return new PaypalPayment();
    }
}
