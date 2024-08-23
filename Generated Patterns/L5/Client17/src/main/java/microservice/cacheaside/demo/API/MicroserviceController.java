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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9790/api/Cache51)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9793/api/Cache53)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9797/api/Cache56)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9800/api/Cache58)

}

