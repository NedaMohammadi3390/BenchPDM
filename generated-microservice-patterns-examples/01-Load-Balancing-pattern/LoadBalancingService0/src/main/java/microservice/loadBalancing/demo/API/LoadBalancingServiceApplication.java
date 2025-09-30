package microservice.loadBalancing.demo.API;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class LoadBalancingServiceApplication  {

public LoadBalancingServiceApplication( RestTemplate restTemplate) {
this. RestTemplate restTemplate =  RestTemplate restTemplate;
this.restTemplate = new restTemplate();

}

private final RestTemplate  restTemplate;
public static void void main(  string[] args) {
SpringApplication.run(ProviderServiceApplication.class, args); 

}

@Bean
@LoadBalanced
public RestTemplate  restTemplate() {
return new RestTemplate(); ;

}

@GetMapping(path = "/call-provider")
public String  callProvider() {
return  restTemplate.getForObject("http://provider-service/hello", String.class); ;

}


}

