package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Worker64")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping
public ArrayList<Pair<String, Integer>> getProperties() {
return null ;

}

@PostMapping
public boolean doService(@RequestBody object[] objects) {
return true ;

}


}

