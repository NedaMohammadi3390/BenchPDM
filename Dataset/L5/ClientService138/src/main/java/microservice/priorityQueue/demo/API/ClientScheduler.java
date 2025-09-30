microservice.priorityQueue.demo.API

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ClientService138Scheduler {

    private final RabbitTemplate rabbitTemplate;

    public ClientService138Scheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTasks() {
    sendTask("Task10",1);
	sendTask("Task3",8);
	sendTask("Task1",2);
	sendTask("Task2",10);
	sendTask("Task7",9);
	sendTask("Task6",6);
	sendTask("Task5",7);
	sendTask("Task4",4);
	sendTask("Task8",5);
	sendTask("Task9",3);
	
    }

    private void sendTask(String data, int priority) {
        MessageProperties props = new MessageProperties();
        props.setPriority(priority);
        Message message = new Message(data.getBytes(), props);
        rabbitTemplate.send("priority-queue", message);
        System.out.println("Sent: " + data + " with priority " + priority);
    }
}
