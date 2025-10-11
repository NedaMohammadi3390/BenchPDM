${PACKAGE}

import org.springframework.web.bind.annotation.*;

@RestController
public class ${SERVICE-NAME} {

    @PostMapping("/${SERVICEURI}")
    public String validate(@RequestBody String message) {
        if (message.length() > 5) {
            return message;
        } else {
            return "Message too short";
        }
    }
}