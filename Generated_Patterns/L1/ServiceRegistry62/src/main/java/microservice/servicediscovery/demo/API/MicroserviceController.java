package microservice.servicediscovery.demo.API;
@RequestMapping("api/v1/ServiceRegistry62")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/service_address")
public Object getServiceAddress(@RequestBody String info) {
return null ;

}

@PostMapping(path = "/register_service")
public Object registerService(@RequestBody String id) {
return null ;

}

@DeleteMapping(path = "/destroy_service")
public Object destroyService(@RequestBody String id) {
return null ;

}

@PutMapping(path = "/update_service")
public Object updateService(@RequestBody String id) {
return null ;

}


}

