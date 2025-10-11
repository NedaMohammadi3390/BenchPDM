${PACKAGE}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ${SERVICE-NAME}Controller {
    SomeEntityClient client;

    @Autowired
    public ${SERVICE-NAME}Controller(SomeEntityClient client) {
        this.client = client;
    }

    @GetMapping
    public SomeEntity get() {
        return client.getOne();
    }
}