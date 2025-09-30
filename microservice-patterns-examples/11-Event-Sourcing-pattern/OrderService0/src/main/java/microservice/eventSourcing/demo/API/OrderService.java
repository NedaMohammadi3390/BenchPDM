package microservice.eventSourcing.demo.API;


import microservice.eventSourcing.demo.API.OrderStatus;
import microservice.eventSourcing.demo.API.OrderRequest;
import microservice.eventSourcing.demo.API.OrderResponse;
import microservice.eventSourcing.demo.API.OrderEvent;
import microservice.eventSourcing.demo.API.KafkaOrderEventPublisher;
import microservice.eventSourcing.demo.API.OrderEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService  {

@Autowired
private OrderEventRepository repository;
@Autowired
private KafkaOrderEventPublisher eventPublisher;
public OrderResponse  placeAnOrder( orderrequest  orderRequest) {
String orderId = UUID.randomUUID().toString().split("-")[0];
        orderRequest.setOrderId(orderId);
        //do request validation and real business logic
        OrderEvent event = new OrderEvent(orderId, OrderStatus.CREATED, "Order created successfully.", LocalDateTime.now());
        saveAndPublishOrderEvent(event); 
return new OrderResponse(orderId, OrderStatus.CREATED); ;

}

public OrderResponse  confirmOrder( string  orderId) {
OrderEvent event = new OrderEvent(orderId, OrderStatus.CONFIRMED, "Order confirmed successfully.", LocalDateTime.now());
        saveAndPublishOrderEvent(event); 
return new OrderResponse(orderId, OrderStatus.CONFIRMED); ;

}

private void  saveAndPublishOrderEvent( orderevent  event) {
repository.save(event);
        eventPublisher.sendOrderEvent(event); 
return null ;

}


}

