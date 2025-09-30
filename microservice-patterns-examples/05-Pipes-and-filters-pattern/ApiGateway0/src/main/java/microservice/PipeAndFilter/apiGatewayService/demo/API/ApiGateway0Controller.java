microservice.PipeAndFilter.apiGatewayService.demo.API

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.camel.ProducerTemplate;

@RestController
@RequestMapping("/api")
public class ApiGateway0Controller {

@Autowired
private ProducerTemplate producerTemplate;

@PostMapping("/send")
public String sendMessage(@RequestBody String message) {
    String response = producerTemplate.requestBody("direct:start", message, String.class);
return response;
    }
}