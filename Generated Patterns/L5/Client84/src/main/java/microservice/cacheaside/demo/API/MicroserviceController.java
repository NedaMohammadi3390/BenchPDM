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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10168/api/Cache254)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10172/api/Cache257)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10176/api/Cache260)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10178/api/Cache261)

}

