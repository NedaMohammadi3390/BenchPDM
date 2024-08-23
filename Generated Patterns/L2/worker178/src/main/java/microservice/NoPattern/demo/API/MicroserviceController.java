package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker178")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:11980/api/Client152/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:11981/api/Client153/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:11983/api/Client154/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response16 = restTemplate.exchange(http://localhost:11984/api/Client155/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11980/api/Client152/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11981/api/Client153/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11983/api/Client154/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11984/api/Client155/post,HTTP.POST,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11980/api/Client152/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11981/api/Client153/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11983/api/Client154/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11984/api/Client155/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11980/api/Client152/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11981/api/Client153/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:11983/api/Client154/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:11984/api/Client155/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

