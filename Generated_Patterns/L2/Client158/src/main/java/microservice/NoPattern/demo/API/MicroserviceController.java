package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client158")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:12070/api/worker181/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:12072/api/worker182/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:12073/api/worker183/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12070/api/worker181/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12072/api/worker182/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12073/api/worker183/post,HTTP.POST,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12070/api/worker181/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12072/api/worker182/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12073/api/worker183/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:12070/api/worker181/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:12072/api/worker182/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:12073/api/worker183/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

