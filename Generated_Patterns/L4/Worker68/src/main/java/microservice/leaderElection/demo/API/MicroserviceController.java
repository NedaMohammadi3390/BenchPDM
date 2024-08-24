package microservice.leaderElection.demo.API;
@RequestMapping("api/v1/Worker68")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
private boolean isLeader = false;
@PostMapping(path = "/set_leader")
public Object setLeader(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9773/api/Leader23/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9774/api/Worker65/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9775/api/Worker66/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9776/api/Worker67/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9778/api/Worker69/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9773/api/Leader23/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9774/api/Worker65/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9775/api/Worker66/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:9776/api/Worker67/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:9778/api/Worker69/post,HTTP.POST,entity,String.class)
return null ;

}

@PostMapping(path = "/id_retrieval")
public boolean idRetrieval(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9773/api/Leader23/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9774/api/Worker65/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9775/api/Worker66/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9776/api/Worker67/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9778/api/Worker69/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9773/api/Leader23/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9774/api/Worker65/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9775/api/Worker66/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:9776/api/Worker67/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:9778/api/Worker69/post,HTTP.POST,entity,String.class)
return false ;

}


}

