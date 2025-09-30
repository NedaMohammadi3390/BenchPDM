package microservice.cacheAside.demo.API;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
public class cacheAsideApplication  {

public static void void main(String[] args) {
   SpringApplication.run(CacheAsideApplication.class, args); 

}


}

