microservice.priorityQueue.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ExternalService77Application {
    public static void main(String[] args) {
        SpringApplication.run(ExternalService77Application.class, args);
    }

    @PostMapping("/external/process")
    public String process(@RequestParam String data) {
        System.out.println("External received: " + data);
        return "Processed " + data;
    }
}
