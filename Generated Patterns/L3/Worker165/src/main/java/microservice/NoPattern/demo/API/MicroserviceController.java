package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker165")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11881/api/Client146/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11883/api/Client147/post,HTTP.POST,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11881/api/Client146/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11883/api/Client147/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11881/api/Client146/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11883/api/Client147/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

