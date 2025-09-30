microservice.PipeAndFilter.filterService.demo.API

import org.springframework.web.bind.annotation.*;

@RestController
public class Filter0Controller {

    @PostMapping("/Filter0")
    public String validate(@RequestBody String message) {
        if (message.length() > 5) {
            return message;
        } else {
            return "Message too short";
        }
    }
}