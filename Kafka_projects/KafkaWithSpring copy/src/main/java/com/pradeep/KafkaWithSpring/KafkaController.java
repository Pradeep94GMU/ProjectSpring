package com.pradeep.KafkaWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer producer;

    @GetMapping
    public String home(){
        return "Hello";
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return "Message sent to Kafka topic!";
    }

    @PostMapping("/payment")
    public String sendPayment(@RequestParam String transactionId, @RequestParam String message) {

        producer.sendPayment(transactionId, message);
        return "Payment transaction sent!";
    }

}
