package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Publisher6")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9207/api/Bus11)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9209/api/Bus12)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9211/api/Bus13)

}

