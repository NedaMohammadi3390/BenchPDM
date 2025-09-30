package microservice.apigateway.demo.API;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/ApiGateway4")
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
ResponseEntity<String> response1= (restTemplate.exchange(http://localhost:9327/api/Worker8/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2= (restTemplate.exchange(http://localhost:9328/api/Worker9/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3= (restTemplate.exchange(http://localhost:9329/api/Worker10/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int int receiving-data() {
ResponseEntity<String> response7= (restTemplate.exchange(http://localhost:9327/api/Worker8/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response8= (restTemplate.exchange(http://localhost:9328/api/Worker9/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response9= (restTemplate.exchange(http://localhost:9329/api/Worker10/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int int updating-data(PathVariable(sid) String id) {
ResponseEntity<String> response4= (restTemplate.exchange(http://localhost:9327/api/Worker8/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response5= (restTemplate.exchange(http://localhost:9328/api/Worker9/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response6= (restTemplate.exchange(http://localhost:9329/api/Worker10/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int int deleting-data(PathVariable(sid) String id) {
ResponseEntity<String> response10= (restTemplate.exchange(http://localhost:9327/api/Worker8/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response11= (restTemplate.exchange(http://localhost:9328/api/Worker9/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12= (restTemplate.exchange(http://localhost:9329/api/Worker10/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

