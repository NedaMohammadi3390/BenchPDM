package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.*;
import content_generation.generator.CustomizedCodeFileGenerator.pipeFilter.ClientGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.pipeFilter.ApiGatewayGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.pipeFilter.FilterGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.sidecar.ExternalServiceGeneration;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.*;

public class PipesAndFilterStrategy extends Strategy {

    public static int filterCounter = 0;
    public static int clientCounter = 0;
    public static int apigatewayCounter = 0;
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    String filterRole = "Filter";
    String apiGatewayRole = "ApiGateway";
    String clientRole = "Client";
    static Map<String, Integer> filterServices = new HashMap<>();
    int apiGateWayPort;
    @Override
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();

        String microserviceName;
        microserviceName = apiGatewayRole + apigatewayCounter++;
        Microservice apiGateway;
        try {
            apiGateway = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.GET, ""),
                            new Pair<>(Microservice.ConnectionType.POST, ""),
                            new Pair<>(Microservice.ConnectionType.PUT, ""),
                            new Pair<>(Microservice.ConnectionType.DELETE, "")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.PipesAndFilters.toString(), Microservice.Role.ApiGateway.toString()),
                    new PipesAndFilterStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(apiGateway);
        apiGateWayPort = apiGateway.getPort();
        //**//
        int clientNumber = random.nextInt(5) + 2;
        for (int i = 0; i < clientNumber; i++) {
            microserviceName = clientRole + clientCounter++;
            Microservice client = null;
            try {
                client = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{},
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.PipesAndFilters.toString(), Microservice.Role.Client.toString()),
                        new PipesAndFilterStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(client);
            client.setConnections((new Pair<>(Microservice.ConnectionType.GET, apiGateway.getURI())));
            client.setConnections((new Pair<>(Microservice.ConnectionType.POST, apiGateway.getURI())));
            client.setConnections((new Pair<>(Microservice.ConnectionType.PUT, apiGateway.getURI())));
            client.setConnections((new Pair<>(Microservice.ConnectionType.DELETE, apiGateway.getURI())));

        }

            int filterNumber = random.nextInt(5) + 2;
            for (int j = 0; j < filterNumber; j++) {
                microserviceName = filterRole + filterCounter++;
                Microservice filter = null;
                try {
                    filter = new Microservice(
                            getPort(),
                            id++,
                            microserviceName,
                            URIGenerator(microserviceName),
                            new Pair[]{
                                    new Pair<>(Microservice.ConnectionType.GET, ""),
                                    new Pair<>(Microservice.ConnectionType.POST, ""),
                                    new Pair<>(Microservice.ConnectionType.PUT, ""),
                                    new Pair<>(Microservice.ConnectionType.DELETE, "")
                            },
                            false,
                            creationTime(),
                            new Pair<>(Microservice.Pattern.PipesAndFilters.toString(), Microservice.Role.Filter.toString()),
                            new PipesAndFilterStrategy()
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                matrices.add(filter);
                filterServices.put(filter.getMicroserviceName(),filter.getPort());
                apiGateway.setConnections(new Pair<>(Microservice.ConnectionType.TO,filter.getURI()));

            }
        return matrices;
    }

    @Override
    public Builder[] fileFiller(Microservice microservice,
                              String role,
                              String microserviceName,
                              String[] connections,
                              Pair<Microservice.ConnectionType, String>[] connectionTypes)
    {
        int n = 1;
        Builder[] builders = new Builder[n];
        switch (role) {
            case "Filter":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 0);
                break;

            case "ApiGateway":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 1);
                break;

            case "Client":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 2);
                break;
        }
        return builders;
    }
    private Builder GenerationClass(Microservice microservice,
                                    String microserviceName,
                                    String[] connections, int id) {
        //Filter service
        if (id == 0) {
            Builder builder = new Builder("microservice." + "PipeAndFilter.filterService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "pipe-filter/filter"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "pipe-filter/filter"));

            builder.setContent(new FilterGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "pipe-filter/filter",
                    "/src/main/java/microservice/PipeAndFilter/filterService/demo/API", builder.getPackageName()));

            return builder;
        }
        else if (id ==1){
            //ApiGateway service
            Builder builder = new Builder("microservice." + "PipeAndFilter.apiGatewayService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "pipe-filter/api-gateway"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "pipe-filter/api-gateway"));

            builder.setContent(new ApiGatewayGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "pipe-filter/api-gateway",
                    "/src/main/java/microservice/PipeAndFilter/apiGatewayService/demo/API",
                    builder.getPackageName(),
                    filterServices));

            return builder;
        }
        else if (id ==2){
            //client service
            Builder builder = new Builder("microservice." + "PipeAndFilter.clientService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "pipe-filter/client"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "pipe-filter/client"));

            builder.setContent(new ClientGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "pipe-filter/client",
                    "/src/main/java/microservice/PipeAndFilter/clientService/demo/API",
                    builder.getPackageName(),
                    apiGateWayPort));

            return builder;
        }
    else {return null;}
    }
}
