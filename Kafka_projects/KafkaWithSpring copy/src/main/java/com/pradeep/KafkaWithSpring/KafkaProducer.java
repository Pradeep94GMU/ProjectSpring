package com.pradeep.KafkaWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {
    private static final String TOPIC = "chat-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TransactionsPRepository transactionsPRepository;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public void sendPayment(String transactionId, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, transactionId, message);
        future.thenAccept(result -> {
            // Handle success
            System.out.println("Message sent successfully: " + result.getRecordMetadata());
            TransactionsP transaction = new TransactionsP();
            transaction.setTransactionId(transactionId);
            transaction.setStatus("SUCCESS");
            transaction.setMessage(message);
            transactionsPRepository.save(transaction);
        }).exceptionally(ex -> {
            // Handle failure
            TransactionsP transaction = new TransactionsP();
            transaction.setTransactionId(transactionId);
            transaction.setStatus("FAILED");
            transaction.setMessage(ex.getMessage());
            transactionsPRepository.save(transaction);
            System.err.println("Failed to send message: " + ex.getMessage());
            return null;
        });
    }
}
