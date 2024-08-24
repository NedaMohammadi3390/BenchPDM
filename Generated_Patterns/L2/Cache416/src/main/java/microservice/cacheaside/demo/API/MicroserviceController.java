package microservice.cacheaside.demo.API;
@RequestMapping("api/v1/Cache416")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@GetMapping
public Object[] getData(@RequestBody int identifier) {
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12008/api/Worker200/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping
public boolean setData(@RequestBody object[] objects) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12008/api/Worker200/post,HTTP.POST,entity,String.class)
return false ;

}


}

