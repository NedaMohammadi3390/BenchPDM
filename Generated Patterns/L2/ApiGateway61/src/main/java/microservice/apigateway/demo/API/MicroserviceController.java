package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway61")
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
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12178/api/Worker169/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12179/api/Worker170/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:12180/api/Worker171/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:12181/api/Worker172/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12178/api/Worker169/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12179/api/Worker170/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12180/api/Worker171/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12181/api/Worker172/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:12178/api/Worker169/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:12179/api/Worker170/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:12180/api/Worker171/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:12181/api/Worker172/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:12178/api/Worker169/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:12179/api/Worker170/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:12180/api/Worker171/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:12181/api/Worker172/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

