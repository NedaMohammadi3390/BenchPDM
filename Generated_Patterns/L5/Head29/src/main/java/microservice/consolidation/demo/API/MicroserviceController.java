package microservice.consolidation.demo.API;
@RequestMapping("api/v1/Head29")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9158/api/Worker101)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9159/api/Worker102)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9160/api/Worker103)

}

