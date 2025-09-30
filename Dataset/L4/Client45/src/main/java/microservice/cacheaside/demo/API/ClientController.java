package microservice.cacheaside.demo.API;




public class ClientController  {

public ClientController() {
connections = new ArrayList<String>();

}

private int id;
private static final int hostId = 0;
private ArrayList<String> connections;
@GetMapping(path = "/all")
public Object[] findAll(  ) {
return service.findAll() ;

}

@GetMapping(path = "/findBySid/{sid}")
public User findBySid(@PathVariable(sid) integer sid) {
return service.findBySid(sid).orElse(null) ;

}

@PostMapping(path = "/add")
public Integer create(@RequestBody user user) {
return service.create(user); ;

}

@PutMapping(path = "/update/{sid}")
public Integer update(@RequestBody user user) {
return service.update(user); ;

}

@DeleteMapping(path = "/delete/{sid}")
public Integer disable(PathVariable(sid) integer sid) {
return service.disable(sid); ;

}


}

