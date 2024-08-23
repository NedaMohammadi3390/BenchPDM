package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker174")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11928/api/Client148/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11932/api/Client149/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11933/api/Client150/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11935/api/Client151/get,HTTP.GET,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11928/api/Client148/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11932/api/Client149/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11933/api/Client150/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11935/api/Client151/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

