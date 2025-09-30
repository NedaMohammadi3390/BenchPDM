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

        restTemplate.postForObject("http://localhost:9048/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9053/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9054/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9055/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9060/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9061/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9062/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9068/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9069/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9075/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9076/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9077/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9085/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9086/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9087/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9094/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9095/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9096/external/process?data=" + data, null, String.class);
		
    }
}
