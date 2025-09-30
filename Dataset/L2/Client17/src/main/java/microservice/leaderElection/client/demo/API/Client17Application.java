microservice.leaderElection.client.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Client17Application {
    public static void main(String[] args) {
        SpringApplication.run(Client17Application.class, args);
    }
}
