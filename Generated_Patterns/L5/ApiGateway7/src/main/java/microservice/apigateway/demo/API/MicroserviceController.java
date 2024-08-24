package microservice.apigateway.demo.API;
@RequestMapping("api/v1/ApiGateway7")
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
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:10614/api/Worker19/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response17 = restTemplate.exchange(http://localhost:10615/api/Worker20/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response18 = restTemplate.exchange(http://localhost:10616/api/Worker21/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response19 = restTemplate.exchange(http://localhost:10617/api/Worker22/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response20 = restTemplate.exchange(http://localhost:10618/api/Worker23/post,HTTP.POST,entity,String.class)
return null ;

}

@GetMapping(path = "/receiving-info")
public int receiving-data() {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10614/api/Worker19/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10615/api/Worker20/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10616/api/Worker21/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10617/api/Worker22/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10618/api/Worker23/get,HTTP.GET,entity,String.class)
return false ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String id) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10614/api/Worker19/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:10615/api/Worker20/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:10616/api/Worker21/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:10617/api/Worker22/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:10618/api/Worker23/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String id) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:10614/api/Worker19/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:10615/api/Worker20/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:10616/api/Worker21/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:10617/api/Worker22/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:10618/api/Worker23/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

