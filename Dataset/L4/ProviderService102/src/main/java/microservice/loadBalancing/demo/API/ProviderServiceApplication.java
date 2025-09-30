package microservice.loadBalancing.demo.API;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class ProviderServiceApplication  {

public static void void main(String[] args ) {
SpringApplication.run(ProviderServiceApplication.class, args); 

}

@GetMapping(path = "/hello")
public String hello() {
return  "Hello from Provider on port " + System.getProperty("server.port"); ;

}


}

