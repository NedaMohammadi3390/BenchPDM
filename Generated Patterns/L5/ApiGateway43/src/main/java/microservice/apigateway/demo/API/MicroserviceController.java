package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway43")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
private ArrayList<String> database;
@PostMapping(path = "/creating_request")
public int creating-request(@RequestBody String info) {
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:10966/api/Worker134/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:10967/api/Worker135/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10966/api/Worker134/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10967/api/Worker135/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10966/api/Worker134/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10967/api/Worker135/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10966/api/Worker134/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10967/api/Worker135/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

