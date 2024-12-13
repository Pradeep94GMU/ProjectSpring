package com.pradeep.Payment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        payment.setStatus("PENDING");

        // Step 2: Simulate a failure if the payment amount is greater than 1000
        if (payment.getAmount() > 1000) {
            payment.setStatus("FAILED"); // Simulating a failure
            return new ResponseEntity<>(paymentRepository.save(payment), HttpStatus.NO_CONTENT);
        } else {
            payment.setStatus("COMPLETED"); // Mark payment as successful
        }

        return new ResponseEntity<>(paymentRepository.save(payment), HttpStatus.CREATED);


    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
