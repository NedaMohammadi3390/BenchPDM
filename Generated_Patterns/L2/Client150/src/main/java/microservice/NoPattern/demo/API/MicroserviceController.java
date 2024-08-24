package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client150")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:11927/api/worker173/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:11929/api/worker174/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:11930/api/worker175/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:11931/api/worker176/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:11934/api/worker177/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11927/api/worker173/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11929/api/worker174/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11930/api/worker175/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11931/api/worker176/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11934/api/worker177/post,HTTP.POST,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11927/api/worker173/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11929/api/worker174/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11930/api/worker175/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11931/api/worker176/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11934/api/worker177/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

