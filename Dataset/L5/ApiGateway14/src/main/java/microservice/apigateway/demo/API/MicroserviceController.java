package microservice.apigateway.demo.API;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/ApiGateway14")
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
ResponseEntity<String> response7= (restTemplate.exchange(http://localhost:10212/api/Worker26/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8= (restTemplate.exchange(http://localhost:10213/api/Worker27/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int int receiving-data() {
ResponseEntity<String> response1= (restTemplate.exchange(http://localhost:10212/api/Worker26/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2= (restTemplate.exchange(http://localhost:10213/api/Worker27/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int int updating-data(PathVariable(sid) String id) {
ResponseEntity<String> response3= (restTemplate.exchange(http://localhost:10212/api/Worker26/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response4= (restTemplate.exchange(http://localhost:10213/api/Worker27/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int int deleting-data(PathVariable(sid) String id) {
ResponseEntity<String> response5= (restTemplate.exchange(http://localhost:10212/api/Worker26/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response6= (restTemplate.exchange(http://localhost:10213/api/Worker27/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

