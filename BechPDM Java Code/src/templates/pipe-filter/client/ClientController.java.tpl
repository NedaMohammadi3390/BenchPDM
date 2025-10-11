${PACKAGE}

@RestController
@RequestMapping("/client")
public class ${SERVICE-NAME}Controller {

    private final RestTemplate restTemplate;

    public ${SERVICE_NAME}(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PutMapping
    public String sendTask() {
        String dispatcherUrl = "http://localhost:${APIGateway-PORT}/ُThisMessage";
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "Task sent to ApiGateway -> " + response.getBody();
    }


    @GetMapping
    public String triggerDispatch() {
        String dispatcherUrl = "http://localhost:${APIGateway-PORT}";
        ResponseEntity<String> response = restTemplate.postForEntity(dispatcherUrl, null, String.class);
        return "ApiGateway response -> " + response.getBody();
    }
}