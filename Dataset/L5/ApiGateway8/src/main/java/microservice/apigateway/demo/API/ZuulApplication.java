package microservice.apigateway.demo.API;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication  {

public static void void main(String[] args) {
   SpringApplication.run(EurekaApplication.class, args); 

}


}

