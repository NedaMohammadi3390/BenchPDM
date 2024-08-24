package microservice.cacheaside.demo.API;
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private static final int hostId = 0;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9624/api/Cache159)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9628/api/Cache162)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9632/api/Cache165)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9634/api/Cache166)

}

