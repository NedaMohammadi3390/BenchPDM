microservice.priorityQueue.demo.API


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DispatcherService16Listener {

    private final RestTemplate restTemplate = new RestTemplate();

    @RabbitListener(queues = "priority-queue")
    public void receive(Message message) {
        String data = new String(message.getBody());
        System.out.println("Dispatching: " + data);

        restTemplate.postForObject("http://localhost:9148/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9154/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9155/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9156/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9160/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9161/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9162/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9166/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9172/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9173/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9174/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9179/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9180/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9181/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9187/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9188/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9189/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9197/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9201/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9208/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9213/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9214/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9215/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9222/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9223/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9230/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9231/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9232/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9240/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9241/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9247/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9248/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9255/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9263/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9270/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9271/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9277/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9285/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9286/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9294/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9302/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9303/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9307/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9308/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9315/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9316/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9317/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9325/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9326/external/process?data=" + data, null, String.class);
		
    }
}
