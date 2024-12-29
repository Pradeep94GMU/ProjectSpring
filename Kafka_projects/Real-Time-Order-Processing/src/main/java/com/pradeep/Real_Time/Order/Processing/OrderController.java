package com.pradeep.Real_Time.Order.Processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderProducer producer;

    @GetMapping
    private String home(){
        return "Hello world";
    }

    @PostMapping("/send")
    public String sendOrder(@RequestParam String region,
                            @RequestParam String orderId,
                            @RequestParam String userId,
                            @RequestParam String message) {
        producer.sendOrder(region, orderId, userId, message);
        return "Order sent for region=" + region;
    }
}

