microservice.saga.demo.API

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/orders")
@Api(value = "Order Commands", description = "Order Commands Related Endpoints", tags = "Order Commands")
public class Order0CommandControllerCommandController {

    private Order0CommandControllerCommandService Order0CommandControllerCommandService;

    public Order0CommandControllerCommandController(Order0CommandControllerCommandService Order0CommandControllerCommandService) {
        this.Order0CommandControllerCommandService = Order0CommandControllerCommandService;
    }

    @PostMapping
    public CompletableFuture<String> createOrder0CommandController(@RequestBody Order0CommandControllerCreateDTO Order0CommandControllerCreateDTO){
        return Order0CommandControllerCommandService.createOrder0CommandController(Order0CommandControllerCreateDTO);
    }
}