microservice.priorityQueue.demo.API

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ClientService73Scheduler {

    private final RabbitTemplate rabbitTemplate;

    public ClientService73Scheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTasks() {
    sendTask("Task6",5);
	sendTask("Task10",8);
	sendTask("Task7",10);
	sendTask("Task4",1);
	sendTask("Task2",6);
	sendTask("Task3",3);
	sendTask("Task9",7);
	sendTask("Task5",9);
	sendTask("Task1",4);
	sendTask("Task8",2);
	
    }

    private void sendTask(String data, int priority) {
        MessageProperties props = new MessageProperties();
        props.setPriority(priority);
        Message message = new Message(data.getBytes(), props);
        rabbitTemplate.send("priority-queue", message);
        System.out.println("Sent: " + data + " with priority " + priority);
    }
}
