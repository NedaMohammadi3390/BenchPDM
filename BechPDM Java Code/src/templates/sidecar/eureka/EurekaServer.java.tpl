${PACKAGE}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class ${SERVICE-NAME}Server {
public static void main(String[] args) {
SpringApplication.run(${SERVICE-NAME}Server.class, args);
}
}