package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway3")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
private ArrayList<String> database;
@PostMapping(path = "/creating_request")
public int creating-request(@RequestBody String info) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9844/api/Worker11/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9845/api/Worker12/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9846/api/Worker13/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:9847/api/Worker14/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:9848/api/Worker15/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9844/api/Worker11/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9845/api/Worker12/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9846/api/Worker13/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9847/api/Worker14/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9848/api/Worker15/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:9844/api/Worker11/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response17 = restTemplate.exchange(http://localhost:9845/api/Worker12/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response18 = restTemplate.exchange(http://localhost:9846/api/Worker13/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response19 = restTemplate.exchange(http://localhost:9847/api/Worker14/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response20 = restTemplate.exchange(http://localhost:9848/api/Worker15/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:9844/api/Worker11/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:9845/api/Worker12/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:9846/api/Worker13/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:9847/api/Worker14/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:9848/api/Worker15/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

