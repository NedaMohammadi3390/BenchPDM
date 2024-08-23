package microservice.cacheaside.demo.API;
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private static final int hostId = 0;
private ArrayList<String> connections;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10012/api/Cache171)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10016/api/Cache174)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10018/api/Cache175)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10020/api/Cache176)

}

