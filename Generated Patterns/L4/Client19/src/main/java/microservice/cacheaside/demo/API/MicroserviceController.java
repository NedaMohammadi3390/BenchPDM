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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9430/api/Cache47)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9434/api/Cache50)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9438/api/Cache53)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9441/api/Cache55)

}

