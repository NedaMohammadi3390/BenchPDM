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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9804/api/Cache60)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9808/api/Cache63)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9811/api/Cache65)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9815/api/Cache68)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9817/api/Cache69)

}

