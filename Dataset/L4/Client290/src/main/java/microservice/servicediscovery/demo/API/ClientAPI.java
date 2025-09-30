package microservice.servicediscovery.demo.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
org.springframework.web.client.RestTemplate;

@Component
public class ClientAPI  {

public ClientAPI() {
connection = new String();

}

private int id;
private static final int hostId = -1;
private String connection;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1= restTemplate.exchangehttp://localhost:9934/api/ServiceRegistry24)
ResponseEntity<String> response2= restTemplate.exchangehttp://localhost:9935/api/Worker129)

}

