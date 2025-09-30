package microservice.serviceDiscovery.demo.API;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/ServiceRegistry25")
@RestController
public class MicroserviceController  {

public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
private ArrayList<String> database;
@GetMapping(path = "/service_address")
public Object getServiceAddress(PathVariable(sid) String info) {
return null ;

}

@PostMapping(path = "/register_service")
public Object registerService(PathVariable(sid) String id) {
return null ;

}

@DeleteMapping(path = "/destroy_service")
public Object destroyService(PathVariable(sid) String id) {
return null ;

}

@PutMapping(path = "/update_service")
public Object updateService(PathVariable(sid) String id) {
return null ;

}


}

