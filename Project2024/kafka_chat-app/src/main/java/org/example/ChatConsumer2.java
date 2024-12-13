package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ChatConsumer2 {

    private static final String TOPIC = "chat-topic";

    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "chat-consumer-group");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
        consumer.subscribe(Collections.singletonList(TOPIC));

        System.out.println("Chat started, ready to read from topic..");

        while(true){
             ConsumerRecords<String, String> consumerRecords  = consumer.poll(Duration.ofMillis(100));
             for( ConsumerRecord<String, String > record : consumerRecords){
                 System.out.println("Received: "+ record.value());
             }
        }


    }
}
