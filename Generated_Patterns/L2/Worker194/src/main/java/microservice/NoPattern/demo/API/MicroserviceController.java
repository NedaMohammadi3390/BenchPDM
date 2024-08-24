package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker194")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:12245/api/Client165/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:12246/api/Client166/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:12247/api/Client167/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:12250/api/Client168/get,HTTP.GET,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12245/api/Client165/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12246/api/Client166/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12247/api/Client167/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12250/api/Client168/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12245/api/Client165/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12246/api/Client166/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:12247/api/Client167/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:12250/api/Client168/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

