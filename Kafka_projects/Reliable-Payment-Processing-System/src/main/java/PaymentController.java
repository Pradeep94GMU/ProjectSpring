import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentProducer producer;

    @GetMapping
    public String home(){
        return "hello";
    }

    @PostMapping
    public String sendPayment(@RequestParam String transactionId, @RequestParam String message) {
        System.out.println("hello1");
        producer.sendPayment(transactionId, message);
        return "Payment transaction sent!";
    }
}
