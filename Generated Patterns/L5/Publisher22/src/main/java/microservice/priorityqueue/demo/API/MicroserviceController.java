package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Publisher22")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9487/api/Bus41)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9489/api/Bus42)

}

