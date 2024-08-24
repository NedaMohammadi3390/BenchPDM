package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker196")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12267/api/Client169/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12267/api/Client169/post,HTTP.POST,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12267/api/Client169/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12267/api/Client169/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

