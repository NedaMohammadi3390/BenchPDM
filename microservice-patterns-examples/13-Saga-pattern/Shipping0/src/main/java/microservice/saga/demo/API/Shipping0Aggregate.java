microservice.saga.demo.API

import microservice.saga.demo.API.CreateShippingCommand;
import microservice.saga.demo.API.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Shipping0AggregateAggregate {

    @AggregateIdentifier
    private String shippingId;

    private String orderId;

    private String paymentId;

    public Shipping0AggregateAggregate() {
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