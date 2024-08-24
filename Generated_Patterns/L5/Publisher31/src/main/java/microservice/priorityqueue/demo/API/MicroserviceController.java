package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Publisher31")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9538/api/Bus60)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9540/api/Bus61)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9542/api/Bus62)

}

