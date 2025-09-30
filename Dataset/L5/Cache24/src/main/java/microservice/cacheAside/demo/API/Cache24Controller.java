package microservice.cacheAside.demo.API;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import microservice.cacheAside.demo.API.User;
import microservice.cacheAside.demo.API.UserService;

@RequestMapping("api/v1/userCache24")
@RestController
public class Cache24Controller  {

public Cache24Controller() {
connections = new ArrayList<String>();

}

@Autowired
private UserService id;
private ArrayList<String>  connections;
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

