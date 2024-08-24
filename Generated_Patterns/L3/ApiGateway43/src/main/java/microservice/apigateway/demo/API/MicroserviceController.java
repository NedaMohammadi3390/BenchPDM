package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway43")
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
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11620/api/Worker129/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11621/api/Worker130/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11622/api/Worker131/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11623/api/Worker132/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11624/api/Worker133/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11620/api/Worker129/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11621/api/Worker130/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11622/api/Worker131/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11623/api/Worker132/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11624/api/Worker133/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:11620/api/Worker129/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response17 = restTemplate.exchange(http://localhost:11621/api/Worker130/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response18 = restTemplate.exchange(http://localhost:11622/api/Worker131/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response19 = restTemplate.exchange(http://localhost:11623/api/Worker132/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response20 = restTemplate.exchange(http://localhost:11624/api/Worker133/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:11620/api/Worker129/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:11621/api/Worker130/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:11622/api/Worker131/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:11623/api/Worker132/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:11624/api/Worker133/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

