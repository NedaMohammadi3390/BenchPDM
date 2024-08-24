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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9943/api/Cache130)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9946/api/Cache132)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9948/api/Cache133)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9952/api/Cache136)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9954/api/Cache137)

}

