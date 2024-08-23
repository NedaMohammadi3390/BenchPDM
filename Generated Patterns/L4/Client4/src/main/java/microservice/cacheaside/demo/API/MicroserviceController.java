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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9368/api/Cache14)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9372/api/Cache17)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9374/api/Cache18)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9377/api/Cache20)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9381/api/Cache23)

}

