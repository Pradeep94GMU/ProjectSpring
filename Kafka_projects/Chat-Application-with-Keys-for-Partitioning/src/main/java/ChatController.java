import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private ChatProducer producer;

    @GetMapping
    public String home(){
        return "Hello";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String userId, @RequestParam String message) {
        producer.sendMessage(userId, message);
        return "Message sent for userId=" + userId;
    }
}

