
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ChatProducer {

    private static final String TOPIC = "chat-topic-miniP2";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String userId, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, userId, message);

        future.thenAccept(result -> {
            System.out.println("Message sent successfully for userId=" + userId + ": " + message);
        }).exceptionally(ex -> {
            System.err.println("Failed to send message for userId=" + userId + ": " + ex.getMessage());
            return null;
        });
    }
}

