package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Publisher24")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9498/api/Bus44)

}

