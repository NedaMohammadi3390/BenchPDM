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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10140/api/Cache239)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10143/api/Cache241)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10145/api/Cache242)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10147/api/Cache243)

}

