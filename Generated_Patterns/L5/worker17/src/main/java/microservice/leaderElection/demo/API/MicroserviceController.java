package microservice.leaderElection.demo.API;
@RequestMapping("api/v1/Worker17")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10306/api/Leader4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10307/api/Worker14/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10308/api/Worker15/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10309/api/Worker16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10311/api/Worker18/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10306/api/Leader4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:10307/api/Worker14/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:10308/api/Worker15/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:10309/api/Worker16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:10311/api/Worker18/post,HTTP.POST,entity,String.class)
return null ;

}

@PostMapping(path = "/id_retrieval")
public boolean idRetrieval(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10306/api/Leader4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10307/api/Worker14/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10308/api/Worker15/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10309/api/Worker16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10311/api/Worker18/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10306/api/Leader4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:10307/api/Worker14/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:10308/api/Worker15/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response9 = restTemplate.exchange(http://localhost:10309/api/Worker16/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response10 = restTemplate.exchange(http://localhost:10311/api/Worker18/post,HTTP.POST,entity,String.class)
return false ;

}


}

