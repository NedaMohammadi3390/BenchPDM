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

        restTemplate.postForObject("http://localhost:9145/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9146/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9152/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9159/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9160/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9165/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9170/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9171/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9172/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9176/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9177/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9185/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9186/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9187/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9192/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9193/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9198/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9199/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9200/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9204/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9210/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9211/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9218/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9226/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9227/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9233/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9234/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9235/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9242/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9250/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9251/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9252/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9260/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9265/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9273/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9274/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9275/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9281/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9288/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9289/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9295/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9296/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9297/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9304/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9305/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9313/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9314/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9318/external/process?data=" + data, null, String.class);
		
    }
}
