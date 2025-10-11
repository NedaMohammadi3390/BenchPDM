${PACKAGE}

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/orders")
@Api(value = "Order Commands", description = "Order Commands Related Endpoints", tags = "Order Commands")
public class ${SERVICE-NAME}CommandController {

    private ${SERVICE-NAME}CommandService ${SERVICE-NAME}CommandService;

    public ${SERVICE-NAME}CommandController(${SERVICE-NAME}CommandService ${SERVICE-NAME}CommandService) {
        this.${SERVICE-NAME}CommandService = ${SERVICE-NAME}CommandService;
    }

    @PostMapping
    public CompletableFuture<String> create${SERVICE-NAME}(@RequestBody ${SERVICE-NAME}CreateDTO ${SERVICE-NAME}CreateDTO){
        return ${SERVICE-NAME}CommandService.create${SERVICE-NAME}(${SERVICE-NAME}CreateDTO);
    }
}