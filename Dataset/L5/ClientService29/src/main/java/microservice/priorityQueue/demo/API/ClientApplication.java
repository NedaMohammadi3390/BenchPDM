microservice.priorityQueue.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ClientService29Application {
    public static void main(String[] args) {
        SpringApplication.run(ClientService29Application.class, args);
    }
}
