microservice.PipeAndFilter.filterService.demo.API

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Filter1Route extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
        .log("some text ...")
        .filter("command")
        .log("....");
    }
}