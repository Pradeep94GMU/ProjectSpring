package com.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSVController {
    private final CSVProducer csvProducer;

    public CSVController(CSVProducer csvProducer) {
        this.csvProducer = csvProducer;
    }

    @GetMapping("/publish-csv")
    public String publishCSV(@RequestParam String fileName) {
        System.out.println("File Name: " + fileName);
        csvProducer.sendMessagesFromCSV(fileName);
        return "CSV data is being sent to Kafka topic.";
    }
}
