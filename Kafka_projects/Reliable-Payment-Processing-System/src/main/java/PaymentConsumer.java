
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void processPayment(String message) {
        System.out.println("Processing payment: " + message);
    }
}

