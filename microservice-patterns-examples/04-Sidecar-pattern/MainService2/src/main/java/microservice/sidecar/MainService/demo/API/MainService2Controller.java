microservice.sidecar.MainService.demo.API

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class MainService2ControllerController {
    SomeEntityClient client;

    @Autowired
    public MainService2ControllerController(SomeEntityClient client) {
        this.client = client;
    }

    @GetMapping
    public SomeEntity get() {
        return client.getOne();
    }
}