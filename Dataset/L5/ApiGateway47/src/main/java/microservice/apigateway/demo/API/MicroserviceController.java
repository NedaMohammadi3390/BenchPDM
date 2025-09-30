package microservice.apigateway.demo.API;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/ApiGateway47")
@RestController
public class MicroserviceController  {

public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
private ArrayList<String> database;
@PostMapping(path = "/creating_request")
public int int creating-request(PathVariable(sid) String info) {
ResponseEntity<String> response10= (restTemplate.exchange(http://localhost:10429/api/Worker90/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response11= (restTemplate.exchange(http://localhost:10430/api/Worker91/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response12= (restTemplate.exchange(http://localhost:10431/api/Worker92/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int int receiving-data() {
ResponseEntity<String> response1= (restTemplate.exchange(http://localhost:10429/api/Worker90/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2= (restTemplate.exchange(http://localhost:10430/api/Worker91/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3= (restTemplate.exchange(http://localhost:10431/api/Worker92/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int int updating-data(PathVariable(sid) String id) {
ResponseEntity<String> response4= (restTemplate.exchange(http://localhost:10429/api/Worker90/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response5= (restTemplate.exchange(http://localhost:10430/api/Worker91/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response6= (restTemplate.exchange(http://localhost:10431/api/Worker92/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int int deleting-data(PathVariable(sid) String id) {
ResponseEntity<String> response7= (restTemplate.exchange(http://localhost:10429/api/Worker90/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response8= (restTemplate.exchange(http://localhost:10430/api/Worker91/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response9= (restTemplate.exchange(http://localhost:10431/api/Worker92/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

