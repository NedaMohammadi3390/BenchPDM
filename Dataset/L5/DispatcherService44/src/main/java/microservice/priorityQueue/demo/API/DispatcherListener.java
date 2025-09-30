microservice.priorityQueue.demo.API


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DispatcherService44Listener {

    private final RestTemplate restTemplate = new RestTemplate();

    @RabbitListener(queues = "priority-queue")
    public void receive(Message message) {
        String data = new String(message.getBody());
        System.out.println("Dispatching: " + data);

        restTemplate.postForObject("http://localhost:9278/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9279/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9285/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9290/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9291/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9299/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9300/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9301/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9305/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9309/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9310/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9311/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9318/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9319/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9320/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9324/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9325/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9329/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9334/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9339/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9340/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9345/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9351/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9352/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9353/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9360/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9361/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9367/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9368/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9373/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9374/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9375/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9381/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9382/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9383/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9389/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9395/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9400/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9401/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9408/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9409/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9417/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9418/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9422/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9423/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9430/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9431/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9432/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9437/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9444/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9445/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9446/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9452/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9456/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9457/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9465/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9472/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9473/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9480/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9485/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9486/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9487/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9495/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9496/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9500/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9501/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9507/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9508/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9513/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9521/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9522/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9523/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9529/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9530/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9538/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9539/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9546/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9547/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9548/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9552/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9553/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9557/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9565/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9566/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9567/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9572/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9573/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9580/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9581/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9586/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9593/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9601/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9602/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9603/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9609/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9614/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9615/external/process?data=" + data, null, String.class);
		
    }
}
