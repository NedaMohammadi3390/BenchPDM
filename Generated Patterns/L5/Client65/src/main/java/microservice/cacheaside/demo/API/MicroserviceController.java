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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10078/api/Cache208)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10082/api/Cache211)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10084/api/Cache212)

}

