${PACKAGE}

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableZuulProxy
public class ${SERVICE-NAME}Service {
public static void main(String[] args) {
new SpringApplicationBuilder(${SERVICE-NAME}Service.class).web(true).run(args);
}
}