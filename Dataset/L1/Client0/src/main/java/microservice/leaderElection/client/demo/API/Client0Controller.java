microservice.leaderElection.client.demo.API

@RestController
@RequestMapping("/client")
public class Client0ControllerController {

    private final RestTemplate restTemplate;

    public ${SERVICE_NAME}Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PutMapping
    public String sendTask() {
        String dispatcherUrl = "http://localhost:9067/Leader2";
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "Task sent to LeaderElection -> " + response.getBody();
    }


    @GetMapping
    public String triggerDispatch() {
        String dispatcherUrl = "http://localhost:9067/Leader2";
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "LeaderElection response -> " + response.getBody();
    }
}