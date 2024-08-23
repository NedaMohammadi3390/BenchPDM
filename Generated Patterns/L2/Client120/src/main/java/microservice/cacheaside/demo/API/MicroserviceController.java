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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11999/api/Cache407)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12002/api/Cache409)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12006/api/Cache412)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12009/api/Cache414)

}

