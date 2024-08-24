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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11988/api/Cache402)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11990/api/Cache403)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11992/api/Cache404)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11994/api/Cache405)

}

