microservice.priorityQueue.demo.API

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ClientService16Scheduler {

    private final RabbitTemplate rabbitTemplate;

    public ClientService16Scheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTasks() {
    sendTask("Task6",6);
	sendTask("Task3",9);
	sendTask("Task8",5);
	sendTask("Task7",2);
	sendTask("Task1",3);
	sendTask("Task4",4);
	sendTask("Task5",1);
	sendTask("Task9",10);
	sendTask("Task2",7);
	sendTask("Task10",8);
	
    }

    private void sendTask(String data, int priority) {
        MessageProperties props = new MessageProperties();
        props.setPriority(priority);
        Message message = new Message(data.getBytes(), props);
        rabbitTemplate.send("priority-queue", message);
        System.out.println("Sent: " + data + " with priority " + priority);
    }
}
