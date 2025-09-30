package microservice.apigateway.demo.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
org.springframework.web.client.RestTemplate;

@Component
public class ClientAPI  {

public ClientAPI() {
connection = new String();

}

private int id;
private static final int hostId = 0;
private String connection;
@Autowired
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response1= restTemplate.exchange(http://localhost:9540/api/ApiGateway0)
ResponseEntity<String> response1= (restTemplate.exchange(http://localhost:9540/api/ApiGateway0/post,HTTP.Post,entity,String.class)
ResponseEntity<String> response2= (restTemplate.exchange(http://localhost:9540/api/ApiGateway0/put,HTTP.Put,entity,String.class)
ResponseEntity<String> response3= (restTemplate.exchange(http://localhost:9540/api/ApiGateway0/get,HTTP.Get,entity,String.class)
ResponseEntity<String> response4= (restTemplate.exchange(http://localhost:9540/api/ApiGateway0/delete,HTTP.Delete,entity,String.class)

}

