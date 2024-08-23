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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10105/api/Cache222)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10107/api/Cache223)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10110/api/Cache225)

}

