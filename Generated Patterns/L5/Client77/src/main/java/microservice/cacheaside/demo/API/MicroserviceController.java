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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10124/api/Cache231)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10127/api/Cache233)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10130/api/Cache235)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10132/api/Cache236)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10135/api/Cache238)

}

