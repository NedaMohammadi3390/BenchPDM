package microservice.eventSourcing.demo.API;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest  {

private String orderId;
private String name;
private String userId;
private int qty;
private double price;

}

