package com.pradeep.Real_Time.Order.Processing;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class OrderProducer {

    private static final String TOPIC = "orders-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrder(String region, String orderId, String userId, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, region, message);
        record.headers().add(new RecordHeader("orderID", orderId.getBytes(StandardCharsets.UTF_8)));
        record.headers().add(new RecordHeader("userID", userId.getBytes(StandardCharsets.UTF_8)));
        record.headers().add(new RecordHeader("timestamp", String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)));

        kafkaTemplate.send(record);
    }
}

