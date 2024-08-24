package microservice.consolidation.demo.API;
@RequestMapping("api/v1/Head2")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9023/api/Worker10)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9024/api/Worker11)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9025/api/Worker12)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9026/api/Worker13)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9027/api/Worker14)

}

