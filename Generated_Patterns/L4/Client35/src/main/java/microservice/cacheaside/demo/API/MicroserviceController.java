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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9529/api/Cache103)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9533/api/Cache106)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9537/api/Cache109)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9539/api/Cache110)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9543/api/Cache113)

}

