package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway44")
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
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:10977/api/Worker136/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:10978/api/Worker137/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:10979/api/Worker138/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:10980/api/Worker139/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10977/api/Worker136/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10978/api/Worker137/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10979/api/Worker138/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10980/api/Worker139/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10977/api/Worker136/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10978/api/Worker137/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:10979/api/Worker138/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:10980/api/Worker139/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:10977/api/Worker136/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:10978/api/Worker137/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:10979/api/Worker138/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:10980/api/Worker139/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

