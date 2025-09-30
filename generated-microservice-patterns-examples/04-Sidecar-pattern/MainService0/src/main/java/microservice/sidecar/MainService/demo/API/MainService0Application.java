microservice.sidecar.MainService.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MainService0ApplicationApplication {
public static void main(String[] args) {
SpringApplication.run(MainService0ApplicationApplication.class, args);
}
}