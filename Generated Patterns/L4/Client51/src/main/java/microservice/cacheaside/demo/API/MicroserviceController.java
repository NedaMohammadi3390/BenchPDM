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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9640/api/Cache168)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9643/api/Cache170)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9646/api/Cache172)

}

