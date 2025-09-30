microservice.priorityQueue.demo.API


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DispatcherService11Listener {

    private final RestTemplate restTemplate = new RestTemplate();

    @RabbitListener(queues = "priority-queue")
    public void receive(Message message) {
        String data = new String(message.getBody());
        System.out.println("Dispatching: " + data);

        restTemplate.postForObject("http://localhost:9077/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9082/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9083/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9087/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9088/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9089/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9095/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9096/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9097/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9105/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9110/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9116/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9117/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9124/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9125/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9126/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9132/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9137/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9138/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9145/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9146/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9150/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9151/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9159/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9163/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9171/external/process?data=" + data, null, String.class);
		
    }
}
