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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9771/api/Cache39)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9775/api/Cache42)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9778/api/Cache44)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9782/api/Cache47)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9784/api/Cache48)

}

