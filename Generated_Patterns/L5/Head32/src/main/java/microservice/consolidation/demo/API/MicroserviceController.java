package microservice.consolidation.demo.API;
@RequestMapping("api/v1/Head32")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9170/api/Worker110)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9171/api/Worker111)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9172/api/Worker112)

}

