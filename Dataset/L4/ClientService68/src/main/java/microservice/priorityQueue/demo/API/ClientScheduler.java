microservice.priorityQueue.demo.API

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ClientService68Scheduler {

    private final RabbitTemplate rabbitTemplate;

    public ClientService68Scheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTasks() {
    sendTask("Task3",8);
	sendTask("Task4",1);
	sendTask("Task7",5);
	sendTask("Task9",3);
	sendTask("Task5",4);
	sendTask("Task2",9);
	sendTask("Task8",7);
	sendTask("Task1",2);
	sendTask("Task10",6);
	sendTask("Task6",10);
	
    }

    private void sendTask(String data, int priority) {
        MessageProperties props = new MessageProperties();
        props.setPriority(priority);
        Message message = new Message(data.getBytes(), props);
        rabbitTemplate.send("priority-queue", message);
        System.out.println("Sent: " + data + " with priority " + priority);
    }
}
