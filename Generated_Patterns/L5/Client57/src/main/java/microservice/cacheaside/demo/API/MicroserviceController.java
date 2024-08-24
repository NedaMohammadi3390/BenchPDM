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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10036/api/Cache185)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10040/api/Cache188)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10044/api/Cache191)

}

