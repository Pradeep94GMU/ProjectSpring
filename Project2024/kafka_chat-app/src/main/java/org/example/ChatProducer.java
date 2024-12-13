package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

public class ChatProducer {

    private static final String  TOPIC = "chat-topic";

    public static void main(String[] args) {

        //setup a prop to connect with kafka server
        Properties prop = new Properties();

        prop.put("bootstrap.servers", "localhost:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");




        //create kafkaProducer pipeline to use this prop
        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);

        //start sending msg so receive user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Chat started please type something..");

        //read all input un till exit

        while(true){

            String message = sc.nextLine();

            if(message.equalsIgnoreCase("exit")){
                break;
            }
            //key is responsible for assigning to different partition based on the key
            String key = "user-"+(int)(Math.random() * 6);
            //take the message and use make a obj of ProducerRecord to send message to topic
            producer.send(new ProducerRecord<>(TOPIC, key, message));
            System.out.println("message send: "+message +" to "+ key);

        }

        producer.close();
        sc.close();

    }
}
