${PACKAGE}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;


@SpringBootApplication
@EnableSidecar
public class ${SERVICE-NAME}Application {
public static void main(String[] args) {
SpringApplication.run(${SERVICE-NAME}Application.class, args);
}
}