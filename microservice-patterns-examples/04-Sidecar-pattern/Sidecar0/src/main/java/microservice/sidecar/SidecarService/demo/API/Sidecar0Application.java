microservice.sidecar.SidecarService.demo.API

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;


@SpringBootApplication
@EnableSidecar
public class Sidecar0ApplicationApplication {
public static void main(String[] args) {
SpringApplication.run(Sidecar0ApplicationApplication.class, args);
}
}