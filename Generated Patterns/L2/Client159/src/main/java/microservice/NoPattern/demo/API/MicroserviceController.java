package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client159")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:12109/api/worker184/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:12110/api/worker185/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:12111/api/worker186/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:12113/api/worker187/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:12115/api/worker188/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12109/api/worker184/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12110/api/worker185/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12111/api/worker186/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12113/api/worker187/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12115/api/worker188/post,HTTP.POST,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12109/api/worker184/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:12110/api/worker185/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:12111/api/worker186/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:12113/api/worker187/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:12115/api/worker188/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

