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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9898/api/Cache106)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9901/api/Cache108)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9904/api/Cache110)

}

