microservice.priorityQueue.demo.API

@RestController
@RequestMapping("/client")
public class ClientController {

    private final RestTemplate restTemplate;

    public ClientService32Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PostMapping("/send")
    public String sendTask(@RequestParam String data, @RequestParam int priority) {
        String dispatcherUrl = "http://localhost:9169/dispatcher/add?data=" + data + "&priority=" + priority;
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "Task sent to Dispatcher -> " + response.getBody();
    }


    @PostMapping("/dispatch")
    public String triggerDispatch() {
        String dispatcherUrl = "http://localhost:9169/dispatcher/dispatch";
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "Dispatcher response -> " + response.getBody();
    }
}