${PACKAGE}

import ${PACKAGE}.CreateShippingCommand;
import ${PACKAGE}.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ${SERVICE-NAME}Aggregate {

    @AggregateIdentifier
    private String shippingId;

    private String orderId;

    private String paymentId;

    public ${SERVICE-NAME}Aggregate() {
    }

    @CommandHandler
    public ShippingAggregate(CreateShippingCommand createShippingCommand){
       // codes
    }

    @EventSourcingHandler
    protected void on(OrderShippedEvent orderShippedEvent){
        // codes
    }
}