package microservice.loadBalancing.demo.API;


import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.DefaultServiceInstance;
import java.util.Arrays;

@Configuration
public class ServiceInstanceConfig  {

@Bean
public static ServiceInstanceListSupplier serviceInstanceListSupplier() {
return new ServiceInstanceListSupplier() {
    @Override
    public String getServiceId() {
        return "provider-service";
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
                        new DefaultServiceInstance("provider1", "provider-service", "localhost", 9012, false),
                        new DefaultServiceInstance("provider2", "provider-service", "localhost", 9013, false),
                        new DefaultServiceInstance("provider3", "provider-service", "localhost", 9014, false),
                        new DefaultServiceInstance("provider4", "provider-service", "localhost", 9015, false),
                        new DefaultServiceInstance("provider5", "provider-service", "localhost", 9016, false),
                        new DefaultServiceInstance("provider6", "provider-service", "localhost", 9017, false),
                        new DefaultServiceInstance("provider7", "provider-service", "localhost", 9019, false),
                        new DefaultServiceInstance("provider8", "provider-service", "localhost", 9020, false),
                        new DefaultServiceInstance("provider9", "provider-service", "localhost", 9021, false),
                        new DefaultServiceInstance("provider10", "provider-service", "localhost", 9023, false),
                        new DefaultServiceInstance("provider11", "provider-service", "localhost", 9024, false)
        ));
    }
}; 
return null ;

}


}

