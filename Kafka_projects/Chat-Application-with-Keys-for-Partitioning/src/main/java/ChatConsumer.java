import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ChatConsumer {

    @KafkaListener(topics = "chat-topic-miniP2", groupId = "chat-group")
    public void consume(ConsumerRecord<String, String> record) {
        String userId = record.key();
        String message = record.value();

        System.out.println("Received message for userId=" + userId + ": " + message);

        // Add further processing logic, like storing in DB or sending to frontend
    }
}
