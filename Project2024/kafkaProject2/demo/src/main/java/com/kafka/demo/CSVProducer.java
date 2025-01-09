package com.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CSVProducer {
    private static final String TOPIC = "csv_topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CSVProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessagesFromCSV(String fileName) {
        try {
            // Use ClassPathResource to load the file
            ClassPathResource resource = new ClassPathResource(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                kafkaTemplate.send(TOPIC, line);
                System.out.println("Sent: " + line);

                // Simulate delay
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
