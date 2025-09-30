microservice.priorityQueue.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ClientService24Application {
    public static void main(String[] args) {
        SpringApplication.run(ClientService24Application.class, args);
    }
}
