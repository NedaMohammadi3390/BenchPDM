package microservice.consolidation.demo.API;
@RequestMapping("api/v1/Head0")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9011/api/Worker0)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9012/api/Worker1)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9013/api/Worker2)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9014/api/Worker3)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9015/api/Worker4)

}

