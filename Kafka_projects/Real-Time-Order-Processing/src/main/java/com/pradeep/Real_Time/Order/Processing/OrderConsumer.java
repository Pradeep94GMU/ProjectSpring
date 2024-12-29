package com.pradeep.Real_Time.Order.Processing;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "orders-topic", groupId = "warehouse-group")
    public void processOrder(ConsumerRecord<String, String> record) {
        String region = record.key();
        String message = record.value();

        Header orderIdHeader = record.headers().lastHeader("orderID");
        Header userIdHeader = record.headers().lastHeader("userID");
        Header timestampHeader = record.headers().lastHeader("timestamp");

        String orderId = new String(orderIdHeader.value());
        String userId = new String(userIdHeader.value());
        String timestamp = new String(timestampHeader.value());

        System.out.println("Processing order for region=" + region +
                ", orderID=" + orderId +
                ", userID=" + userId +
                ", timestamp=" + timestamp +
                ", message=" + message);
    }
}

