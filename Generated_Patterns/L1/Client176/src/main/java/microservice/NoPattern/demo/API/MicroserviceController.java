package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client176")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12422/api/worker203/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12423/api/worker204/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12425/api/worker205/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12422/api/worker203/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12423/api/worker204/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12425/api/worker205/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

