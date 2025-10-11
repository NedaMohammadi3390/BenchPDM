${PACKAGE}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ${SERVICE-NAME}WithSpringCloudApplication {

public static void main(String[] args) {
SpringApplication.run(${SERVICE-NAME}WithSpringCloudApplication.class, args);
}

}