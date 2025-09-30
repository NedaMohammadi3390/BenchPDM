microservice.priorityQueue.demo.API

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ClientService168Scheduler {

    private final RabbitTemplate rabbitTemplate;

    public ClientService168Scheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTasks() {
    sendTask("Task2",5);
	sendTask("Task1",10);
	sendTask("Task5",6);
	sendTask("Task6",1);
	sendTask("Task4",9);
	sendTask("Task7",4);
	sendTask("Task8",3);
	sendTask("Task3",2);
	sendTask("Task10",7);
	
    }

    private void sendTask(String data, int priority) {
        MessageProperties props = new MessageProperties();
        props.setPriority(priority);
        Message message = new Message(data.getBytes(), props);
        rabbitTemplate.send("priority-queue", message);
        System.out.println("Sent: " + data + " with priority " + priority);
    }
}
