microservice.saga.demo.API

import microservice.saga.demo.API.OrderStatus;
import microservice.saga.demo.API.CreateOrderCommand;
import microservice.saga.demo.API.OrderCreateDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class Order0CommandServiceCommandService {

    private final CommandGateway commandGateway;

    public Order0CommandServiceCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createOrder(Order0CommandServiceCreateDTO orderCreateDTO) {
        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), orderCreateDTO.getItemType(),
                orderCreateDTO.getPrice(), orderCreateDTO.getCurrency(), String.valueOf(OrderStatus.CREATED)));
    }
}