package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Publisher4")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
busConnections = new ArrayList<String>();
collectingConnections = new ArrayList<String>();

}

private int id;
private static final ArrayList<String> busConnections;
private ArrayList<String> collectingConnections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9195/api/Bus6)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9197/api/Bus7)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9199/api/Bus8)

}

