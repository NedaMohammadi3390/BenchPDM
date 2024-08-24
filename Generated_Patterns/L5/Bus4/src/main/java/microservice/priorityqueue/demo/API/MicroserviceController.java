package microservice.priorityqueue.demo.API;
@RequestMapping("api/v1/Bus4")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
@PostMapping
public boolean setProcess(@RequestBody object[] objects) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9381/api/Worker4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9382/api/Worker5/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9383/api/Worker6/post,HTTP.POST,entity,String.class)
return true ;

}

@GetMapping
public boolean setProcess(@RequestBody object[] objects) {
return response;

}


}

