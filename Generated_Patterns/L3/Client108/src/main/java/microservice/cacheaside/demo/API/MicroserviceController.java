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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11411/api/Cache342)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11415/api/Cache345)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11418/api/Cache347)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11421/api/Cache349)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11423/api/Cache350)

}

