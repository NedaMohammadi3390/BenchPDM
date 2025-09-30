package microservice.eventSourcing.demo.API;


import microservice.eventSourcing.demo.API.OrderRequest;
import microservice.eventSourcing.demo.API.OrderResponse;
import microservice.eventSourcing.demo.API.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/ordersOrderService0")
@RestController
public class OrderService0Controller  {

@Autowired
private OrderService orderService;
@PostMapping(path = "/place")
public ResponseEntity<OrderResponse> placeOrder(@RequestBody orderrequest  orderRequest) {
try {
            OrderResponse orderResponse = orderService.placeAnOrder(orderRequest);
            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
return null ;

}

@PutMapping(path = "/confirm/{orderId}")
public ResponseEntity<OrderResponse> confirmOrder(@PathVariable string  orderId) {
return null ;

}


}

