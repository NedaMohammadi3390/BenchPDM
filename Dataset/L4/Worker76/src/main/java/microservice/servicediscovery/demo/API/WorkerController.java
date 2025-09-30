package microservice.servicediscovery.demo.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/Worker76")
@RestController
public class WorkerController  {

public WorkerController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int int receiving-data(PathVariable(sid) String info) {
ResponseEntity<String> response1= restTemplate.exchange(http://localhost:9755/api/ServiceRegistry4/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int int inserting-data(PathVariable(sid) String info) {
ResponseEntity<String> response2= restTemplate.exchange(http://localhost:9755/api/ServiceRegistry4/post,HTTP.POST,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int int deleting-data(PathVariable(sid) String info) {
ResponseEntity<String> response4= restTemplate.exchange(http://localhost:9755/api/ServiceRegistry4/delete,HTTP.DELETE,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int int updating-data(PathVariable(sid) String info) {
ResponseEntity<String> response3= restTemplate.exchange(http://localhost:9755/api/ServiceRegistry4/put,HTTP.PUT,entity,String.class)
return null ;

}


}

