package microservice.leaderElection.demo.API;
@RequestMapping("api/v1/Leader12")
@RestController
public class MicroserviceController  {
public MicroserviceController() {
connections = new ArrayList<String>();

}

private int id;
private ArrayList<String> connections;
private boolean isLeader = true;
@PostMapping(path = "/set_leader")
public Object setLeader(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9730/api/Worker32/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9731/api/Worker33/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9732/api/Worker34/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9733/api/Worker35/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9734/api/Worker36/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9730/api/Worker32/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9731/api/Worker33/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9732/api/Worker34/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:9733/api/Worker35/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:9734/api/Worker36/post,HTTP.POST,entity,String.class)
return null ;

}

@PostMapping(path = "/id_retrieval")
public boolean idRetrieval(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9730/api/Worker32/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9731/api/Worker33/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9732/api/Worker34/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9733/api/Worker35/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9734/api/Worker36/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9730/api/Worker32/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9731/api/Worker33/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9732/api/Worker34/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:9733/api/Worker35/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:9734/api/Worker36/post,HTTP.POST,entity,String.class)
return false ;

}


}

