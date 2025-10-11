${PACKAGE}

import ${PACKAGE}.OrderStatus;
import ${PACKAGE}.CreateOrderCommand;
import ${PACKAGE}.OrderCreateDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ${SERVICE-NAME}CommandService {

    private final CommandGateway commandGateway;

    public ${SERVICE-NAME}CommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createOrder(${SERVICE-NAME}CreateDTO orderCreateDTO) {
        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), orderCreateDTO.getItemType(),
                orderCreateDTO.getPrice(), orderCreateDTO.getCurrency(), String.valueOf(OrderStatus.CREATED)));
    }
}