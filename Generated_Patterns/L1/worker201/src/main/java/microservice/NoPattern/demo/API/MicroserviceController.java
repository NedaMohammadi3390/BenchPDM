package microservice.NoPattern.demo.API;
@RequestMapping("api/v1/worker201")
@RestController
public class MicroserviceController  {
public MicroserviceController() {

}

private int id;
@PutMapping(path = "/updating-info")
public int updating-data(@RequestBody String info) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:12392/api/Client173/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:12394/api/Client174/put,HTTP.PUT,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:12396/api/Client175/put,HTTP.PUT,entity,String.class)
return null ;

}

@DeleteMapping(path = "/deleting-info")
public int deleting-data(@RequestBody String info) {
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:12392/api/Client173/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:12394/api/Client174/delete,HTTP.DELETE,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:12396/api/Client175/delete,HTTP.DELETE,entity,String.class)
return null ;

}


}

