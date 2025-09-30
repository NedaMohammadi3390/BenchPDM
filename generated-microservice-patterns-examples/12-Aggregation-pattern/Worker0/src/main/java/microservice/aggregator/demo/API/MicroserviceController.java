package microservice.aggregator.demo.API;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/Worker0")
@RestController
public class MicroserviceController  {

public MicroserviceController() {

}

private int id;
@GetMapping(path = "/getting-data")
public Object[] getProperties() {
return null ;

}

@PostMapping(path = "/inserting-data")
public Object[] getProperties() {
return null ;

}

@PutMapping(path = "/updating-info")
public Object[] getProperties() {
return null ;

}

@DeleteMapping(path = "/deleting-info")
public Object[] getProperties() {
return null ;

}


}

