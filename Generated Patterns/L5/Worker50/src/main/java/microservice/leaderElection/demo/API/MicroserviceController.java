package microservice.leaderElection.demo.API;
@RequestMapping("api/v1/Worker50")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10354/api/Leader16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10356/api/Worker51/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10354/api/Leader16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10356/api/Worker51/post,HTTP.POST,entity,String.class)
return null ;

}

@PostMapping(path = "/id_retrieval")
public boolean idRetrieval(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10354/api/Leader16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10356/api/Worker51/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10354/api/Leader16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10356/api/Worker51/post,HTTP.POST,entity,String.class)
return false ;

}


}

