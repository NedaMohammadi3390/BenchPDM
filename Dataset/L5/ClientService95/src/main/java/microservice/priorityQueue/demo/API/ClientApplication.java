microservice.priorityQueue.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ClientService95Application {
    public static void main(String[] args) {
        SpringApplication.run(ClientService95Application.class, args);
    }
}
