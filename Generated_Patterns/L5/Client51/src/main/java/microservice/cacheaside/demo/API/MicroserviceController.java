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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10000/api/Cache165)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10003/api/Cache167)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10006/api/Cache169)

}

