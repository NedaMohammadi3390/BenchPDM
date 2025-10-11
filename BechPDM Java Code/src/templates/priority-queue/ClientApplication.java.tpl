${PACKAGE}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ${SERVICE_NAME} {
    public static void main(String[] args) {
        SpringApplication.run(${SERVICE_NAME}.class, args);
    }
}
