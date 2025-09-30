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
                        new DefaultServiceInstance("provider5", "provider-service", "localhost", 9017, false),
                        new DefaultServiceInstance("provider6", "provider-service", "localhost", 9018, false),
                        new DefaultServiceInstance("provider7", "provider-service", "localhost", 9019, false),
                        new DefaultServiceInstance("provider8", "provider-service", "localhost", 9020, false),
                        new DefaultServiceInstance("provider9", "provider-service", "localhost", 9021, false),
                        new DefaultServiceInstance("provider10", "provider-service", "localhost", 9023, false),
                        new DefaultServiceInstance("provider11", "provider-service", "localhost", 9024, false),
                        new DefaultServiceInstance("provider12", "provider-service", "localhost", 9025, false),
                        new DefaultServiceInstance("provider13", "provider-service", "localhost", 9027, false),
                        new DefaultServiceInstance("provider14", "provider-service", "localhost", 9028, false),
                        new DefaultServiceInstance("provider15", "provider-service", "localhost", 9029, false),
                        new DefaultServiceInstance("provider16", "provider-service", "localhost", 9031, false),
                        new DefaultServiceInstance("provider17", "provider-service", "localhost", 9032, false),
                        new DefaultServiceInstance("provider18", "provider-service", "localhost", 9034, false),
                        new DefaultServiceInstance("provider19", "provider-service", "localhost", 9035, false),
                        new DefaultServiceInstance("provider20", "provider-service", "localhost", 9036, false),
                        new DefaultServiceInstance("provider21", "provider-service", "localhost", 9038, false),
                        new DefaultServiceInstance("provider22", "provider-service", "localhost", 9039, false),
                        new DefaultServiceInstance("provider23", "provider-service", "localhost", 9040, false),
                        new DefaultServiceInstance("provider24", "provider-service", "localhost", 9042, false),
                        new DefaultServiceInstance("provider25", "provider-service", "localhost", 9043, false),
                        new DefaultServiceInstance("provider26", "provider-service", "localhost", 9044, false),
                        new DefaultServiceInstance("provider27", "provider-service", "localhost", 9045, false),
                        new DefaultServiceInstance("provider28", "provider-service", "localhost", 9046, false),
                        new DefaultServiceInstance("provider29", "provider-service", "localhost", 9048, false),
                        new DefaultServiceInstance("provider30", "provider-service", "localhost", 9049, false),
                        new DefaultServiceInstance("provider31", "provider-service", "localhost", 9050, false),
                        new DefaultServiceInstance("provider32", "provider-service", "localhost", 9051, false),
                        new DefaultServiceInstance("provider33", "provider-service", "localhost", 9052, false),
                        new DefaultServiceInstance("provider34", "provider-service", "localhost", 9053, false),
                        new DefaultServiceInstance("provider35", "provider-service", "localhost", 9055, false),
                        new DefaultServiceInstance("provider36", "provider-service", "localhost", 9056, false),
                        new DefaultServiceInstance("provider37", "provider-service", "localhost", 9057, false),
                        new DefaultServiceInstance("provider38", "provider-service", "localhost", 9059, false),
                        new DefaultServiceInstance("provider39", "provider-service", "localhost", 9060, false),
                        new DefaultServiceInstance("provider40", "provider-service", "localhost", 9062, false),
                        new DefaultServiceInstance("provider41", "provider-service", "localhost", 9063, false),
                        new DefaultServiceInstance("provider42", "provider-service", "localhost", 9065, false),
                        new DefaultServiceInstance("provider43", "provider-service", "localhost", 9066, false),
                        new DefaultServiceInstance("provider44", "provider-service", "localhost", 9068, false),
                        new DefaultServiceInstance("provider45", "provider-service", "localhost", 9069, false),
                        new DefaultServiceInstance("provider46", "provider-service", "localhost", 9071, false),
                        new DefaultServiceInstance("provider47", "provider-service", "localhost", 9072, false)
        ));
    }
}; 
return null ;

}


}

