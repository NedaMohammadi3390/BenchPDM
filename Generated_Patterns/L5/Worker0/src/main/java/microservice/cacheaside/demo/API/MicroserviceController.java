package microservice.cacheaside.demo.API;
@RequestMapping("api/v1/Worker0")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping
public Object[] getData(@RequestBody int identifier) {
return null ;

}

@PostMapping
public boolean setData(@RequestBody object[] objects) {
return false ;

}


}

