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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9708/api/Cache4)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9712/api/Cache7)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9716/api/Cache10)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9718/api/Cache11)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9720/api/Cache12)

}

