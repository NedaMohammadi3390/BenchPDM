microservice.priorityQueue.demo.API


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DispatcherService8Listener {

    private final RestTemplate restTemplate = new RestTemplate();

    @RabbitListener(queues = "priority-queue")
    public void receive(Message message) {
        String data = new String(message.getBody());
        System.out.println("Dispatching: " + data);

        restTemplate.postForObject("http://localhost:9089/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9095/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9099/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9105/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9106/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9107/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9114/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9115/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9116/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9120/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9121/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9122/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9127/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9128/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9134/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9139/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9140/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9146/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9147/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9151/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9155/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9156/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9161/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9162/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9163/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9168/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9173/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9174/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9175/external/process?data=" + data, null, String.class);
		
    }
}
