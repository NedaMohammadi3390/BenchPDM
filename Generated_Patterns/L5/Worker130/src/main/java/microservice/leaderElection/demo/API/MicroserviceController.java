package microservice.leaderElection.demo.API;
@RequestMapping("api/v1/Worker130")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10460/api/Leader42/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10462/api/Worker131/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10463/api/Worker132/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10460/api/Leader42/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10462/api/Worker131/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10463/api/Worker132/post,HTTP.POST,entity,String.class)
return null ;

}

@PostMapping(path = "/id_retrieval")
public boolean idRetrieval(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:10460/api/Leader42/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:10462/api/Worker131/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:10463/api/Worker132/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:10460/api/Leader42/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:10462/api/Worker131/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:10463/api/Worker132/post,HTTP.POST,entity,String.class)
return false ;

}


}

