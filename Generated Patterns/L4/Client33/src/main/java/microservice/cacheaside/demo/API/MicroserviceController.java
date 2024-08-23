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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9514/api/Cache94)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9518/api/Cache97)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9521/api/Cache99)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9524/api/Cache101)

}

