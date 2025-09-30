package microservice.eventSourcing.demo.API;


import microservice.eventSourcing.demo.API.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderEventRepository extends MongoRepository<OrderEvent,String>  {


}

