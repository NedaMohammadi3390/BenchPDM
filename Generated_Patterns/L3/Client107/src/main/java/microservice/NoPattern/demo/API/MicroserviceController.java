package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client107")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11186/api/worker129/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11187/api/worker130/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11190/api/worker131/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11193/api/worker132/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11195/api/worker133/get,HTTP.GET,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11186/api/worker129/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11187/api/worker130/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11190/api/worker131/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11193/api/worker132/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11195/api/worker133/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

