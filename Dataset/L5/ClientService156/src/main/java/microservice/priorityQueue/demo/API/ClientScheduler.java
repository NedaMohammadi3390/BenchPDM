microservice.priorityQueue.demo.API

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ClientService156Scheduler {

    private final RabbitTemplate rabbitTemplate;

    public ClientService156Scheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTasks() {
    sendTask("Task8",1);
	sendTask("Task10",3);
	sendTask("Task6",4);
	sendTask("Task7",6);
	sendTask("Task9",5);
	sendTask("Task5",10);
	sendTask("Task2",9);
	sendTask("Task4",2);
	sendTask("Task3",7);
	sendTask("Task1",8);
	
    }

    private void sendTask(String data, int priority) {
        MessageProperties props = new MessageProperties();
        props.setPriority(priority);
        Message message = new Message(data.getBytes(), props);
        rabbitTemplate.send("priority-queue", message);
        System.out.println("Sent: " + data + " with priority " + priority);
    }
}
