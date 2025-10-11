${PACKAGE}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ${SERVICE_NAME} {
    public static void main(String[] args) {
        SpringApplication.run(${SERVICE_NAME}.class, args);
    }

    @PostMapping("/external/process")
    public String process(@RequestParam String data) {
        System.out.println("External received: " + data);
        return "Processed " + data;
    }
}
