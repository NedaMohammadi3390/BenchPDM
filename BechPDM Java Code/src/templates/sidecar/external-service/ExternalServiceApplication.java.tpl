${PACKAGE}

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class ${SERVICE-NAME}Service {
public static void main(String[] args) {
new SpringApplicationBuilder(${SERVICE-NAME}Service.class).web(true).run(args);
}
}