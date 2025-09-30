microservice.priorityQueue.demo.API


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DispatcherService37Listener {

    private final RestTemplate restTemplate = new RestTemplate();

    @RabbitListener(queues = "priority-queue")
    public void receive(Message message) {
        String data = new String(message.getBody());
        System.out.println("Dispatching: " + data);

        restTemplate.postForObject("http://localhost:9267/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9268/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9269/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9275/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9279/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9280/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9281/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9289/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9290/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9297/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9298/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9299/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9306/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9307/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9312/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9320/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9321/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9328/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9332/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9339/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9346/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9347/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9355/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9356/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9357/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9364/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9365/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9366/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9374/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9375/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9381/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9385/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9386/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9393/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9399/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9400/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9408/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9409/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9410/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9415/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9420/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9421/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9429/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9436/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9443/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9447/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9448/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9456/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9457/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9458/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9463/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9464/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9470/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9474/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9480/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9481/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9485/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9486/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9494/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9495/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9496/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9504/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9505/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9506/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9512/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9513/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9514/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9521/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9522/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9523/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9531/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9532/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9536/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9537/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9538/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9542/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9543/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9544/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9551/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9552/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9553/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9558/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9559/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9560/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9564/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9565/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9570/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9571/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9572/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9577/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9582/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9589/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9590/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9596/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9597/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9598/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9602/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9603/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9609/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9610/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9615/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9616/external/process?data=" + data, null, String.class);
		restTemplate.postForObject("http://localhost:9617/external/process?data=" + data, null, String.class);
		
    }
}
