microservice.PipeAndFilter.apiGatewayService.demo.API

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ApiGateway0Route extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       from("direct:start")
        .to("http://Filter1:9014/Filter1
        .to("http://Filter0:9013/Filter0
        .log("Final response: ${body}");


    }
}

