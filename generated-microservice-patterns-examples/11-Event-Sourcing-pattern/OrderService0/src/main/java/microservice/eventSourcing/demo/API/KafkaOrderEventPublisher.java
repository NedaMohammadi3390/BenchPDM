package microservice.eventSourcing.demo.API;


import microservice.eventSourcing.demo.API.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderEventPublisher  {

@Autowired
private KafkaTemplate<String, OrderEvent> kafkaTemplate;
@Value(path = "${order.event.topicName}")
private String topicName;
public void void  sendOrderEvent( orderevent  orderEvent) {
kafkaTemplate.send(topicName, orderEvent.getOrderId(), orderEvent); 

}


}

