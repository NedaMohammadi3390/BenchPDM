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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12035/api/Cache430)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12038/api/Cache432)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12042/api/Cache435)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12044/api/Cache436)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12046/api/Cache437)

}

