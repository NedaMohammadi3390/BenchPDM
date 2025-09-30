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
                        new DefaultServiceInstance("provider3", "provider-service", "localhost", 9015, false),
                        new DefaultServiceInstance("provider4", "provider-service", "localhost", 9016, false),
                        new DefaultServiceInstance("provider5", "provider-service", "localhost", 9017, false),
                        new DefaultServiceInstance("provider6", "provider-service", "localhost", 9019, false),
                        new DefaultServiceInstance("provider7", "provider-service", "localhost", 9020, false),
                        new DefaultServiceInstance("provider8", "provider-service", "localhost", 9021, false),
                        new DefaultServiceInstance("provider9", "provider-service", "localhost", 9022, false),
                        new DefaultServiceInstance("provider10", "provider-service", "localhost", 9023, false),
                        new DefaultServiceInstance("provider11", "provider-service", "localhost", 9025, false),
                        new DefaultServiceInstance("provider12", "provider-service", "localhost", 9026, false),
                        new DefaultServiceInstance("provider13", "provider-service", "localhost", 9027, false),
                        new DefaultServiceInstance("provider14", "provider-service", "localhost", 9028, false),
                        new DefaultServiceInstance("provider15", "provider-service", "localhost", 9029, false),
                        new DefaultServiceInstance("provider16", "provider-service", "localhost", 9030, false),
                        new DefaultServiceInstance("provider17", "provider-service", "localhost", 9032, false),
                        new DefaultServiceInstance("provider18", "provider-service", "localhost", 9033, false),
                        new DefaultServiceInstance("provider19", "provider-service", "localhost", 9034, false),
                        new DefaultServiceInstance("provider20", "provider-service", "localhost", 9036, false),
                        new DefaultServiceInstance("provider21", "provider-service", "localhost", 9037, false),
                        new DefaultServiceInstance("provider22", "provider-service", "localhost", 9039, false),
                        new DefaultServiceInstance("provider23", "provider-service", "localhost", 9040, false)
        ));
    }
}; 
return null ;

}


}

