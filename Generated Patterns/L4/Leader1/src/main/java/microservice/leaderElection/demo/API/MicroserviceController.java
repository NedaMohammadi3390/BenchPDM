package microservice.leaderElection.demo.API;
@RequestMapping("api/v1/Leader1")
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
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9689/api/Worker2/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9690/api/Worker3/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9691/api/Worker4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9692/api/Worker5/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9689/api/Worker2/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9690/api/Worker3/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9691/api/Worker4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9692/api/Worker5/post,HTTP.POST,entity,String.class)
return null ;

}

@PostMapping(path = "/id_retrieval")
public boolean idRetrieval(@RequestBody int id) {
ResponseEntity<String> response1 = restTemplate.exchange(http://localhost:9689/api/Worker2/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response2 = restTemplate.exchange(http://localhost:9690/api/Worker3/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response3 = restTemplate.exchange(http://localhost:9691/api/Worker4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response4 = restTemplate.exchange(http://localhost:9692/api/Worker5/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response5 = restTemplate.exchange(http://localhost:9689/api/Worker2/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response6 = restTemplate.exchange(http://localhost:9690/api/Worker3/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response7 = restTemplate.exchange(http://localhost:9691/api/Worker4/post,HTTP.POST,entity,String.class)
ResponseEntity<String> response8 = restTemplate.exchange(http://localhost:9692/api/Worker5/post,HTTP.POST,entity,String.class)
return false ;

}


}

