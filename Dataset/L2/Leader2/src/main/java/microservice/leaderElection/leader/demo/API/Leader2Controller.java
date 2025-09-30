microservice.leaderElection.leader.demo.API

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.leader.Context;
import org.springframework.integration.leader.event.OnGrantedEvent;
import org.springframework.integration.leader.event.OnRevokedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/Leader2Controller")
public class Leader2ControllerController {


private static final Logger log = LoggerFactory.getLogger(LeaderController.class);
private final String host;

@Value("${spring.cloud.kubernetes.leader.role}")
private String role;

private Context context;

private ManagerContext managerContext;

public Leader2ControllerController(ManagerContext managerContext) throws UnknownHostException {
this.host = InetAddress.getLocalHost().getHostName();
this.managerContext =managerContext;
}

/**
* Return a message whether this instance is a leader or not.
* @return info
*/
@GetMapping
public String getInfo() {
if (this.context == null) {
return String.format("I am '%s' but I am not a leader of the '%s'", this.host, this.role);
}
return String.format("I am '%s' and I am the leader of the '%s'", this.host, this.role);
}

/**
* PUT request to try and revoke a leadership of this instance. If the instance is not
* a leader, leadership cannot be revoked. Thus "HTTP Bad Request" response. If the
* instance is a leader, it must have a leadership context instance which can be used
* to give up the leadership.
* @return info about leadership
*/
@PutMapping
public ResponseEntity<String> revokeLeadership() {
    if (this.context == null) {
    String message = String.format("Cannot revoke leadership because '%s' is not a leader", this.host);
    return ResponseEntity.badRequest().body(message);
    }
    this.context.yield();
    String message = String.format("Leadership revoked for '%s'", this.host);
    return ResponseEntity.ok(message);
    }

    /**
    * Handle a notification that this instance has become a leader.
    * @param event on granted event
    */
    @EventListener
   //codes

    }