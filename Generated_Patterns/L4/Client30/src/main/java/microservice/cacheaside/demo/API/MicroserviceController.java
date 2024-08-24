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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9500/api/Cache86)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9502/api/Cache87)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9504/api/Cache88)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9506/api/Cache89)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9510/api/Cache92)

}

