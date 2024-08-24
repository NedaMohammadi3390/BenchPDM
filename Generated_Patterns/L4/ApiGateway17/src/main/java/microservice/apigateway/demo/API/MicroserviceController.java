package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway17")
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
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9975/api/Worker49/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9976/api/Worker50/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9977/api/Worker51/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9978/api/Worker52/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9975/api/Worker49/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9976/api/Worker50/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9977/api/Worker51/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9978/api/Worker52/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:9975/api/Worker49/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:9976/api/Worker50/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:9977/api/Worker51/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:9978/api/Worker52/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:9975/api/Worker49/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:9976/api/Worker50/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:9977/api/Worker51/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:9978/api/Worker52/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

