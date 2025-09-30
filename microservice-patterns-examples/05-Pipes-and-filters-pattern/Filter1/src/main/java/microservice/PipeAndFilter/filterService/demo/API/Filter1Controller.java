microservice.PipeAndFilter.filterService.demo.API

import org.springframework.web.bind.annotation.*;

@RestController
public class Filter1Controller {

    @PostMapping("/Filter1")
    public String validate(@RequestBody String message) {
        if (message.length() > 5) {
            return message;
        } else {
            return "Message too short";
        }
    }
}