microservice.priorityQueue.demo.API


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DispatcherService4Listener {

    private final RestTemplate restTemplate = new RestTemplate();

    @RabbitListener(queues = "priority-queue")
    public void receive(Message message) {
        String data = new String(message.getBody());
        System.out.println("Dispatching: " + data);

        restTemplate.postForObject("http://localhost:9045/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9049/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9050/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9056/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9057/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9058/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9063/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9068/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9069/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9074/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9075/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9080/external/process?data=" + data, null, String.class);
		
    }
}
