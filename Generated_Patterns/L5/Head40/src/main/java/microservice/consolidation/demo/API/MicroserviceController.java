package microservice.consolidation.demo.API;
@RequestMapping("api/v1/Head40")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9208/api/Worker140)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9209/api/Worker141)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9210/api/Worker142)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9211/api/Worker143)

}

