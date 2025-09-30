microservice.sidecar.externalService.demo.API

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/sidecar")
public class ExternalService1ControllerController {

    @PostMapping{value = /add}
    public String create(@RequestBody User user){
        return service.update(user);
    }
}