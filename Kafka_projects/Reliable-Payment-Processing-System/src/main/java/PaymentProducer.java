import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentProducer {

    private static final String TOPIC = "payment-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private TransactionsPRepository transactionsPRepository;

    public void sendPayment(String transactionId, String message) {
        System.out.println("hello2");
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, transactionId, message);
        System.out.println("hello3");
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
