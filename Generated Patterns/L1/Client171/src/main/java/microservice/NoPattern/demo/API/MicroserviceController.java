package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client171")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12354/api/worker199/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12356/api/worker200/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12354/api/worker199/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12356/api/worker200/post,HTTP.POST,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12354/api/worker199/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12356/api/worker200/put,HTTP.PUT,entity,String.class)
return null ;

}


}

