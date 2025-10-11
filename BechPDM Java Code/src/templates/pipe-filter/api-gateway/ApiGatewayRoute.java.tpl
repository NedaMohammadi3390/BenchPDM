${PACKAGE}

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ${SERVICE-NAME} extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       ${TEXT}
    }
}

