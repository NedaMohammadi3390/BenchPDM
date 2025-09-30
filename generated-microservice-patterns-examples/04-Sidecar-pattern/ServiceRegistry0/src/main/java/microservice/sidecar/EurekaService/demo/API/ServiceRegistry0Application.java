microservice.sidecar.EurekaService.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistry0ApplicationServer {
public static void main(String[] args) {
SpringApplication.run(ServiceRegistry0ApplicationServer.class, args);
}
}