package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker205")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12424/api/Client176/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12424/api/Client176/post,HTTP.POST,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12424/api/Client176/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

