package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client135")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11576/api/worker150/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11578/api/worker151/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11581/api/worker152/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11583/api/worker153/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11588/api/worker154/get,HTTP.GET,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11576/api/worker150/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11578/api/worker151/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11581/api/worker152/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11583/api/worker153/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11588/api/worker154/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

