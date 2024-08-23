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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9734/api/Cache21)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9736/api/Cache22)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9739/api/Cache24)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9741/api/Cache25)

}

