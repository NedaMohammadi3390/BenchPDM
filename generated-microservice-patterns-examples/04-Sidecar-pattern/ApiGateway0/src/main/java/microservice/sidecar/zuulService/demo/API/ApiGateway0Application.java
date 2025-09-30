microservice.sidecar.zuulService.demo.API

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableZuulProxy
public class ApiGateway0ApplicationService {
public static void main(String[] args) {
new SpringApplicationBuilder(ApiGateway0ApplicationService.class).web(true).run(args);
}
}