package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway55")
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
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12123/api/Worker152/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:12124/api/Worker153/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:12125/api/Worker154/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:12126/api/Worker155/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:12127/api/Worker156/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12123/api/Worker152/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12124/api/Worker153/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12125/api/Worker154/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12126/api/Worker155/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12127/api/Worker156/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:12123/api/Worker152/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response17 = restTemplate.exchange(http://localhost:12124/api/Worker153/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response18 = restTemplate.exchange(http://localhost:12125/api/Worker154/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response19 = restTemplate.exchange(http://localhost:12126/api/Worker155/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response20 = restTemplate.exchange(http://localhost:12127/api/Worker156/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:12123/api/Worker152/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:12124/api/Worker153/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:12125/api/Worker154/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:12126/api/Worker155/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:12127/api/Worker156/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

