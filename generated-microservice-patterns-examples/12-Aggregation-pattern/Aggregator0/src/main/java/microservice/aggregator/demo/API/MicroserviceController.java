package microservice.aggregator.demo.API;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/Aggregator0")
@RestController
public class MicroserviceController  {

public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1restTemplate.exchangehttp://localhost:9011/api/Worker0
ResponseEntity<String> response2restTemplate.exchangehttp://localhost:9012/api/Worker1

}

