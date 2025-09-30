microservice.sidecar.externalService.demo.API

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class ExternalService2ApplicationService {
public static void main(String[] args) {
new SpringApplicationBuilder(ExternalService2ApplicationService.class).web(true).run(args);
}
}