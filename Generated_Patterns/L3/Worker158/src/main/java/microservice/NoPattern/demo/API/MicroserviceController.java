package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker158")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@GetMapping(path = "/receive_info")
public int receiving-data(@RequestBody String info) {
ResponseEntity<String> response11 = restTemplate.exchange(http://localhost:11715/api/Client141/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response12 = restTemplate.exchange(http://localhost:11717/api/Client142/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response13 = restTemplate.exchange(http://localhost:11719/api/Client143/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response14 = restTemplate.exchange(http://localhost:11721/api/Client144/get,HTTP.GET,entity,String.class)
ResponseEntity<String> response15 = restTemplate.exchange(http://localhost:11723/api/Client145/get,HTTP.GET,entity,String.class)
return null ;

}

@PostMapping(path = "/inserting-info")
public int inserting-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:11715/api/Client141/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:11717/api/Client142/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:11719/api/Client143/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:11721/api/Client144/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:11723/api/Client145/post,HTTP.POST,entity,String.class)
return null ;

}

@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:11715/api/Client141/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:11717/api/Client142/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:11719/api/Client143/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:11721/api/Client144/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:11723/api/Client145/put,HTTP.PUT,entity,String.class)
return null ;

}


}

