package microservice.eventSourcing.demo.API;


import microservice.eventSourcing.demo.API.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("collection = {OrderEvents}")
public class OrderEvent  {

public OrderEvent(String orderId, OrderStatus status, String details, LocalDateTime eventTimestamp) {
this.String orderId = String orderId;
this.OrderStatus status = OrderStatus status;
this.String details = String details;
this.LocalDateTime eventTimestamp = LocalDateTime eventTimestamp;
this.orderId = new  orderId();
this.status = new  status();
this.details = new  details();
this.eventTimestamp = new  eventTimestamp();

}

@Id
private String id;
private String orderId;
private OrderStatus status;
private String details;
private LocalDateTime eventTimestamp;

}

