microservice.PipeAndFilter.clientService.demo.API

@RestController
@RequestMapping("/client")
public class ${SERVICE-NAME}Controller {

    private final RestTemplate restTemplate;

    public Client1Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PutMapping
    public String sendTask() {
        String dispatcherUrl = "http://localhost:0/ُThisMessage";
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "Task sent to ApiGateway -> " + response.getBody();
    }


    @GetMapping
    public String triggerDispatch() {
        String dispatcherUrl = "http://localhost:0";
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "ApiGateway response -> " + response.getBody();
    }
}