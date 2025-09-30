microservice.priorityQueue.demo.API


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DispatcherService1Listener {

    private final RestTemplate restTemplate = new RestTemplate();

    @RabbitListener(queues = "priority-queue")
    public void receive(Message message) {
        String data = new String(message.getBody());
        System.out.println("Dispatching: " + data);

        restTemplate.postForObject("http://localhost:9029/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9036/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9037/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9042/external/process?data=" + data, null, String.class);
		
    }
}
