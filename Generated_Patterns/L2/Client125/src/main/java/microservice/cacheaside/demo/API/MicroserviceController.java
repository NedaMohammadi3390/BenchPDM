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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12051/api/Cache440)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12054/api/Cache442)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12058/api/Cache445)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12061/api/Cache447)

}

