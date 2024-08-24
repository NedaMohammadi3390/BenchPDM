package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway47")
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
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:11003/api/Worker148/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response17 = restTemplate.exchange(http://localhost:11004/api/Worker149/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response18 = restTemplate.exchange(http://localhost:11005/api/Worker150/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response19 = restTemplate.exchange(http://localhost:11006/api/Worker151/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response20 = restTemplate.exchange(http://localhost:11007/api/Worker152/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11003/api/Worker148/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11004/api/Worker149/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11005/api/Worker150/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11006/api/Worker151/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11007/api/Worker152/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11003/api/Worker148/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11004/api/Worker149/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11005/api/Worker150/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11006/api/Worker151/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11007/api/Worker152/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:11003/api/Worker148/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:11004/api/Worker149/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:11005/api/Worker150/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:11006/api/Worker151/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:11007/api/Worker152/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

