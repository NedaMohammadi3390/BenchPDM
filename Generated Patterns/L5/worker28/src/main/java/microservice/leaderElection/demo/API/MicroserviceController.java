package microservice.leaderElection.demo.API;
@RequestMapping("api/v1/Worker28")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10322/api/Leader8/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10323/api/Worker26/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10324/api/Worker27/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10326/api/Worker29/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10327/api/Worker30/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10322/api/Leader8/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:10323/api/Worker26/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:10324/api/Worker27/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:10326/api/Worker29/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:10327/api/Worker30/post,HTTP.POST,entity,String.class)
return null ;

}

@PostMapping(path = "/id_retrieval")
public boolean idRetrieval(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10322/api/Leader8/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10323/api/Worker26/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10324/api/Worker27/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10326/api/Worker29/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10327/api/Worker30/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10322/api/Leader8/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:10323/api/Worker26/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:10324/api/Worker27/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:10326/api/Worker29/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:10327/api/Worker30/post,HTTP.POST,entity,String.class)
return false ;

}


}

