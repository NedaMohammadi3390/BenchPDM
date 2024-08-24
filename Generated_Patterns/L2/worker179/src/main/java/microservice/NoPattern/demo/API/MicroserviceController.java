package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker179")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11980/api/Client152/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11981/api/Client153/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11983/api/Client154/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11984/api/Client155/get,HTTP.GET,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11980/api/Client152/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11981/api/Client153/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11983/api/Client154/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11984/api/Client155/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

