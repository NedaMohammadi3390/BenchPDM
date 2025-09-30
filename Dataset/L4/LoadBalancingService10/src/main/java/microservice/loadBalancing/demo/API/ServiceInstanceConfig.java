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
                        new DefaultServiceInstance("provider4", "provider-service", "localhost", 9016, false),
                        new DefaultServiceInstance("provider5", "provider-service", "localhost", 9017, false),
                        new DefaultServiceInstance("provider6", "provider-service", "localhost", 9018, false),
                        new DefaultServiceInstance("provider7", "provider-service", "localhost", 9020, false),
                        new DefaultServiceInstance("provider8", "provider-service", "localhost", 9021, false),
                        new DefaultServiceInstance("provider9", "provider-service", "localhost", 9022, false),
                        new DefaultServiceInstance("provider10", "provider-service", "localhost", 9023, false),
                        new DefaultServiceInstance("provider11", "provider-service", "localhost", 9025, false),
                        new DefaultServiceInstance("provider12", "provider-service", "localhost", 9026, false),
                        new DefaultServiceInstance("provider13", "provider-service", "localhost", 9027, false),
                        new DefaultServiceInstance("provider14", "provider-service", "localhost", 9028, false),
                        new DefaultServiceInstance("provider15", "provider-service", "localhost", 9029, false),
                        new DefaultServiceInstance("provider16", "provider-service", "localhost", 9031, false),
                        new DefaultServiceInstance("provider17", "provider-service", "localhost", 9032, false),
                        new DefaultServiceInstance("provider18", "provider-service", "localhost", 9033, false),
                        new DefaultServiceInstance("provider19", "provider-service", "localhost", 9035, false),
                        new DefaultServiceInstance("provider20", "provider-service", "localhost", 9036, false),
                        new DefaultServiceInstance("provider21", "provider-service", "localhost", 9037, false),
                        new DefaultServiceInstance("provider22", "provider-service", "localhost", 9038, false),
                        new DefaultServiceInstance("provider23", "provider-service", "localhost", 9039, false),
                        new DefaultServiceInstance("provider24", "provider-service", "localhost", 9041, false),
                        new DefaultServiceInstance("provider25", "provider-service", "localhost", 9042, false),
                        new DefaultServiceInstance("provider26", "provider-service", "localhost", 9043, false),
                        new DefaultServiceInstance("provider27", "provider-service", "localhost", 9044, false),
                        new DefaultServiceInstance("provider28", "provider-service", "localhost", 9045, false),
                        new DefaultServiceInstance("provider29", "provider-service", "localhost", 9047, false),
                        new DefaultServiceInstance("provider30", "provider-service", "localhost", 9048, false),
                        new DefaultServiceInstance("provider31", "provider-service", "localhost", 9049, false),
                        new DefaultServiceInstance("provider32", "provider-service", "localhost", 9050, false),
                        new DefaultServiceInstance("provider33", "provider-service", "localhost", 9051, false),
                        new DefaultServiceInstance("provider34", "provider-service", "localhost", 9053, false),
                        new DefaultServiceInstance("provider35", "provider-service", "localhost", 9054, false),
                        new DefaultServiceInstance("provider36", "provider-service", "localhost", 9056, false),
                        new DefaultServiceInstance("provider37", "provider-service", "localhost", 9057, false),
                        new DefaultServiceInstance("provider38", "provider-service", "localhost", 9058, false),
                        new DefaultServiceInstance("provider39", "provider-service", "localhost", 9059, false),
                        new DefaultServiceInstance("provider40", "provider-service", "localhost", 9060, false),
                        new DefaultServiceInstance("provider41", "provider-service", "localhost", 9061, false),
                        new DefaultServiceInstance("provider42", "provider-service", "localhost", 9063, false),
                        new DefaultServiceInstance("provider43", "provider-service", "localhost", 9064, false),
                        new DefaultServiceInstance("provider44", "provider-service", "localhost", 9066, false),
                        new DefaultServiceInstance("provider45", "provider-service", "localhost", 9067, false),
                        new DefaultServiceInstance("provider46", "provider-service", "localhost", 9068, false),
                        new DefaultServiceInstance("provider47", "provider-service", "localhost", 9070, false),
                        new DefaultServiceInstance("provider48", "provider-service", "localhost", 9071, false),
                        new DefaultServiceInstance("provider49", "provider-service", "localhost", 9072, false),
                        new DefaultServiceInstance("provider50", "provider-service", "localhost", 9073, false),
                        new DefaultServiceInstance("provider51", "provider-service", "localhost", 9074, false),
                        new DefaultServiceInstance("provider52", "provider-service", "localhost", 9075, false),
                        new DefaultServiceInstance("provider53", "provider-service", "localhost", 9077, false),
                        new DefaultServiceInstance("provider54", "provider-service", "localhost", 9078, false),
                        new DefaultServiceInstance("provider55", "provider-service", "localhost", 9080, false),
                        new DefaultServiceInstance("provider56", "provider-service", "localhost", 9081, false),
                        new DefaultServiceInstance("provider57", "provider-service", "localhost", 9083, false),
                        new DefaultServiceInstance("provider58", "provider-service", "localhost", 9084, false),
                        new DefaultServiceInstance("provider59", "provider-service", "localhost", 9085, false),
                        new DefaultServiceInstance("provider60", "provider-service", "localhost", 9086, false),
                        new DefaultServiceInstance("provider61", "provider-service", "localhost", 9087, false),
                        new DefaultServiceInstance("provider62", "provider-service", "localhost", 9089, false),
                        new DefaultServiceInstance("provider63", "provider-service", "localhost", 9090, false),
                        new DefaultServiceInstance("provider64", "provider-service", "localhost", 9091, false),
                        new DefaultServiceInstance("provider65", "provider-service", "localhost", 9092, false),
                        new DefaultServiceInstance("provider66", "provider-service", "localhost", 9093, false),
                        new DefaultServiceInstance("provider67", "provider-service", "localhost", 9095, false),
                        new DefaultServiceInstance("provider68", "provider-service", "localhost", 9096, false),
                        new DefaultServiceInstance("provider69", "provider-service", "localhost", 9097, false),
                        new DefaultServiceInstance("provider70", "provider-service", "localhost", 9098, false),
                        new DefaultServiceInstance("provider71", "provider-service", "localhost", 9099, false),
                        new DefaultServiceInstance("provider72", "provider-service", "localhost", 9101, false),
                        new DefaultServiceInstance("provider73", "provider-service", "localhost", 9102, false),
                        new DefaultServiceInstance("provider74", "provider-service", "localhost", 9103, false),
                        new DefaultServiceInstance("provider75", "provider-service", "localhost", 9104, false),
                        new DefaultServiceInstance("provider76", "provider-service", "localhost", 9105, false),
                        new DefaultServiceInstance("provider77", "provider-service", "localhost", 9106, false),
                        new DefaultServiceInstance("provider78", "provider-service", "localhost", 9108, false),
                        new DefaultServiceInstance("provider79", "provider-service", "localhost", 9109, false),
                        new DefaultServiceInstance("provider80", "provider-service", "localhost", 9110, false),
                        new DefaultServiceInstance("provider81", "provider-service", "localhost", 9111, false),
                        new DefaultServiceInstance("provider82", "provider-service", "localhost", 9112, false),
                        new DefaultServiceInstance("provider83", "provider-service", "localhost", 9113, false),
                        new DefaultServiceInstance("provider84", "provider-service", "localhost", 9115, false),
                        new DefaultServiceInstance("provider85", "provider-service", "localhost", 9116, false),
                        new DefaultServiceInstance("provider86", "provider-service", "localhost", 9117, false),
                        new DefaultServiceInstance("provider87", "provider-service", "localhost", 9118, false),
                        new DefaultServiceInstance("provider88", "provider-service", "localhost", 9119, false),
                        new DefaultServiceInstance("provider89", "provider-service", "localhost", 9121, false),
                        new DefaultServiceInstance("provider90", "provider-service", "localhost", 9122, false),
                        new DefaultServiceInstance("provider91", "provider-service", "localhost", 9124, false),
                        new DefaultServiceInstance("provider92", "provider-service", "localhost", 9125, false),
                        new DefaultServiceInstance("provider93", "provider-service", "localhost", 9126, false),
                        new DefaultServiceInstance("provider94", "provider-service", "localhost", 9127, false),
                        new DefaultServiceInstance("provider95", "provider-service", "localhost", 9128, false),
                        new DefaultServiceInstance("provider96", "provider-service", "localhost", 9130, false),
                        new DefaultServiceInstance("provider97", "provider-service", "localhost", 9131, false),
                        new DefaultServiceInstance("provider98", "provider-service", "localhost", 9132, false),
                        new DefaultServiceInstance("provider99", "provider-service", "localhost", 9133, false),
                        new DefaultServiceInstance("provider100", "provider-service", "localhost", 9134, false),
                        new DefaultServiceInstance("provider101", "provider-service", "localhost", 9135, false),
                        new DefaultServiceInstance("provider102", "provider-service", "localhost", 9137, false),
                        new DefaultServiceInstance("provider103", "provider-service", "localhost", 9138, false),
                        new DefaultServiceInstance("provider104", "provider-service", "localhost", 9139, false),
                        new DefaultServiceInstance("provider105", "provider-service", "localhost", 9140, false),
                        new DefaultServiceInstance("provider106", "provider-service", "localhost", 9141, false)
        ));
    }
}; 
return null ;

}


}

