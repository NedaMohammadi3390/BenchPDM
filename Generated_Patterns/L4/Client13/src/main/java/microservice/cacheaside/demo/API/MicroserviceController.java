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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9404/api/Cache33)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9407/api/Cache35)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9409/api/Cache36)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9413/api/Cache39)

}

