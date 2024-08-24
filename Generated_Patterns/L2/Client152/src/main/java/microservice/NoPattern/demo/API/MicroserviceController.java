package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client152")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11978/api/worker178/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11979/api/worker179/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11982/api/worker180/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11978/api/worker178/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11979/api/worker179/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11982/api/worker180/post,HTTP.POST,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11978/api/worker178/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11979/api/worker179/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11982/api/worker180/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

