package com.pradeep.SagaOrch.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/saga")
public class SagaOrchestratorController {

    private final String ORDER_SERVICE_URL = "http://localhost:8081/api/orders";
    private final String PAYMENT_SERVICE_URL = "http://localhost:8082/api/payments";
    private final String INVENTORY_SERVICE_URL = "http://localhost:8080/api/inventory";

    @PostMapping("/place-order")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        // Step 1: Create Order
        CustomerOrder customerOrder = new CustomerOrder(orderRequest.getProductName(), orderRequest.getQuantity(), orderRequest.getPrice());
        ResponseEntity<CustomerOrder> orderResponse = new RestTemplate().postForEntity(ORDER_SERVICE_URL, customerOrder, CustomerOrder.class);

        if (orderResponse.getStatusCode() != HttpStatus.CREATED) {
            System.out.println(orderResponse.getStatusCode());
            return "Order creation failed";
        }

        // Step 2: Process Payment
        Payment payment = new Payment(orderResponse.getBody().getId(), orderRequest.getPrice());
        ResponseEntity<Payment> paymentResponse = new RestTemplate().postForEntity(PAYMENT_SERVICE_URL, payment, Payment.class);

        if (paymentResponse.getStatusCode() != HttpStatus.CREATED) {
            // Handle payment failure (e.g., cancel order)
            return "Payment processing failed";
        }

        // Step 3: Update Inventory
        Inventory inventoryUpdate = new Inventory(orderRequest.getProductName(), orderRequest.getQuantity());
        ResponseEntity<Inventory> inventoryResponse = new RestTemplate().postForEntity(INVENTORY_SERVICE_URL, inventoryUpdate, Inventory.class);

        if (inventoryResponse.getStatusCode() != HttpStatus.CREATED) {
            // Handle inventory failure (e.g., refund payment)
            return "Inventory update failed";
        }

        return "Order placed successfully!";
    }
}
