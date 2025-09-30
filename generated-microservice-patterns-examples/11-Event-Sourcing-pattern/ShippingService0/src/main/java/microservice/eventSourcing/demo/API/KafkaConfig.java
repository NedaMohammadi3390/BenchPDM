package microservice.eventSourcing.demo.API;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig  {

@Value(path = "${shipping.event.topicName}")
private String topicName;
public NewTopic  createTopic(@Bean  ) {
return new NewTopic(topicName,3,(short)1); ;

}


}

