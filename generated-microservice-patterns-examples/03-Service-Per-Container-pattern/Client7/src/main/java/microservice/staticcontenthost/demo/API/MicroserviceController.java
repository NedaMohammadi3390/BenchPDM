package microservice.staticcontenthost.demo.API;


import org.springframework.web.bind.annotation.PostMapping;

public class MicroserviceController  {

public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private static final int hostId = 0;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1restTemplate.exchangehttp://localhost:9025/api/Storage3
ResponseEntity<String> response2restTemplate.exchangehttp://localhost:9010/api/Storage0
ResponseEntity<String> response3restTemplate.exchangehttp://localhost:9015/api/Storage1
ResponseEntity<String> response4restTemplate.exchangehttp://localhost:9018/api/Storage2

}

