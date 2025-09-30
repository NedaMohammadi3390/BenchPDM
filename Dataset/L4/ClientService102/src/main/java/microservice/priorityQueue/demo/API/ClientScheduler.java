microservice.priorityQueue.demo.API

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ClientService102Scheduler {

    private final RabbitTemplate rabbitTemplate;

    public ClientService102Scheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTasks() {
    sendTask("Task8",10);
	sendTask("Task10",9);
	sendTask("Task9",7);
	sendTask("Task1",5);
	sendTask("Task3",2);
	sendTask("Task7",6);
	sendTask("Task2",1);
	sendTask("Task6",3);
	sendTask("Task4",8);
	sendTask("Task5",4);
	
    }

    private void sendTask(String data, int priority) {
        MessageProperties props = new MessageProperties();
        props.setPriority(priority);
        Message message = new Message(data.getBytes(), props);
        rabbitTemplate.send("priority-queue", message);
        System.out.println("Sent: " + data + " with priority " + priority);
    }
}
