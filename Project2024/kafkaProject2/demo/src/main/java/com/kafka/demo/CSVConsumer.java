package com.kafka.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CSVConsumer {
    @KafkaListener(topics = "csv_topic", groupId = "csv_group")
    public void consume(String message) {
        System.out.println("Received: " + message);
    }
}
