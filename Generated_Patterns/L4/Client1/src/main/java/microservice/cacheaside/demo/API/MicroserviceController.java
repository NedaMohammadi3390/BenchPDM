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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9343/api/Cache0)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9347/api/Cache3)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9351/api/Cache6)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9355/api/Cache9)

}

