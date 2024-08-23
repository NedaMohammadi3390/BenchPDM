package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway53")
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
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11699/api/Worker148/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11700/api/Worker149/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11701/api/Worker150/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11699/api/Worker148/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11700/api/Worker149/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11701/api/Worker150/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11699/api/Worker148/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:11700/api/Worker149/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:11701/api/Worker150/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11699/api/Worker148/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11700/api/Worker149/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11701/api/Worker150/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

