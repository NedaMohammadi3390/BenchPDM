package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Publisher42")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11207/api/Bus81)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11209/api/Bus82)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11211/api/Bus83)

}

