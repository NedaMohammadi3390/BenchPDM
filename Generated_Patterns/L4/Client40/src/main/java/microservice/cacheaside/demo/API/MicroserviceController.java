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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9554/api/Cache117)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9556/api/Cache118)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9560/api/Cache121)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9564/api/Cache124)

}

