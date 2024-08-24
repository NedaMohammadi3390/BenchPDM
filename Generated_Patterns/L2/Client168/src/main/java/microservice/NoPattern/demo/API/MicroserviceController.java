package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/Client168")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:12244/api/worker192/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:12248/api/worker193/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:12249/api/worker194/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12244/api/worker192/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12248/api/worker193/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12249/api/worker194/post,HTTP.POST,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12244/api/worker192/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12248/api/worker193/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12249/api/worker194/put,HTTP.PUT,entity,String.class)
return null ;

}


}

