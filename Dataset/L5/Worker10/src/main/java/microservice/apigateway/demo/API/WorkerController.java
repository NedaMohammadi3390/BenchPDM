package microservice.apigateway.demo.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/Worker10")
@RestController
public class WorkerController  {

public WorkerController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int int receiving-data(PathVariable(sid) String info) {
return null ;

}

@PostMapping(path = "/inserting-info")
public int int inserting-data(PathVariable(sid) String info) {
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int int deleting-data(PathVariable(sid) String info) {
return null ;

}

@PutMapping(path = "/updating-info")
public int int updating-data(PathVariable(sid) String info) {
return null ;

}


}

