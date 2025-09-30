microservice.leaderElection.client.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Client22Application {
    public static void main(String[] args) {
        SpringApplication.run(Client22Application.class, args);
    }
}
