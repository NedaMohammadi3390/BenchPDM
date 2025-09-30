package microservice.eventSourcing.demo.API;


import microservice.eventSourcing.demo.API.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse  {

private String orderId;
private OrderStatus status;

}

