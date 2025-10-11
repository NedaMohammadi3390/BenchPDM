package strategy;

import content_generation.Builder;
import content_generation.generator.ConfigFileGenerator;
import content_generation.generator.CustomizedCodeFileGenerator.ambassador.*;
import content_generation.generator.POMGenerator;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.*;

public class AmbassadorStrategy extends Strategy {
    public static int clientCounter = 0;
    public static int ambassadorCounter = 0;
    public static int externalServiceCounter = 0;
    public static ClientInfo clientinfo;
    public static AmbassadorInfo ambassadorInfo;
    String clientRole = "Client";
    String ambassadorRole = "Ambassador";
    String externalServiceRole = "ExternalService";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;

    @Override
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();
        ArrayList<Microservice> clientMatrices = new ArrayList<>();
        ArrayList<Microservice> ambassadorMatrices = new ArrayList<>();
        Map<String, Map<Integer, List<String>>> ambassadorMap = new HashMap<>();
        Map<String, Map<Integer, List<String>>> externalServiceMap = new HashMap<>();

        String microserviceName;
        int clientNumber = random.nextInt(2) + 1;
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
                        new Pair<>(Microservice.Pattern.Ambassador.toString(), Microservice.Role.Client.toString()),
                        new AmbassadorStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(client);
            clientMatrices.add(client);
        }


        int ambassadorNumber = random.nextInt(2) + 1;
        for (int j = 0; j < ambassadorNumber; j++) {
            microserviceName = ambassadorRole + ambassadorCounter++;
            Microservice ambassador = null;
            try {
                ambassador = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, ""),
                                new Pair<>(Microservice.ConnectionType.GET, ""),
                                new Pair<>(Microservice.ConnectionType.PUT, ""),
                                new Pair<>(Microservice.ConnectionType.DELETE, "")
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.Ambassador.toString(), Microservice.Role.Ambassador.toString()),
                        new AmbassadorStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(ambassador);
            ambassadorMatrices.add(ambassador);

            microserviceName = externalServiceRole + externalServiceCounter++;
            Microservice externalService = null;
            try {
                externalService = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, ""),
                                new Pair<>(Microservice.ConnectionType.GET, ""),
                                new Pair<>(Microservice.ConnectionType.PUT, ""),
                                new Pair<>(Microservice.ConnectionType.DELETE, "")
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.Ambassador.toString(), Microservice.Role.ExternalService.toString()),
                        new AmbassadorStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(externalService);
            ambassadorInfo = new AmbassadorInfo(ambassador.getMicroserviceName(), ambassador.getPort());
            ambassadorInfo.addexternalService(externalService.getMicroserviceName(),externalService.getPort(),externalService.getId());

            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.POST, externalService.getURI()));
            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.GET, externalService.getURI()));
            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.PUT, externalService.getURI()));
            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, externalService.getURI()));

            externalServiceMap
                    .computeIfAbsent(externalService.getMicroserviceName(), k -> new HashMap<>())
                    .computeIfAbsent(externalService.getPort(), k -> new ArrayList<>())
                    .add(ambassador.getMicroserviceName());
        }


        for (Microservice clint : clientMatrices) {
            clientinfo = new ClientInfo(clint.getMicroserviceName(), clint.getPort());
            for (Microservice amba : ambassadorMatrices) {

                clientinfo.addAmbassador(amba.getMicroserviceName(),amba.getPort(),amba.getId());

                clint.setConnections(new Pair<>(Microservice.ConnectionType.POST, amba.getURI()));
                clint.setConnections(new Pair<>(Microservice.ConnectionType.GET, amba.getURI()));
                clint.setConnections(new Pair<>(Microservice.ConnectionType.PUT, amba.getURI()));
                clint.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, amba.getURI()));
            }
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
        int n=1;
        Builder[] builders = new Builder[n];
        switch (role) {
            case "Client":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 0);
                break;

            case "Ambassador":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 1);
                break;

            case "ExternalService":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 2);
                break;
        }
        return builders;
    }
//**//
private Builder GenerationClass(Microservice microservice,
                                String microserviceName,
                                String[] connections, int id) {
    //Client service
    if (id == 0) {
        Builder builder = new Builder("microservice." + "Ambassador.client" + ".demo.API");
        //generating pom.xml file.
        builder.setContent(new POMGenerator(
                microserviceName, Main.getPath2(), "ambassador/client"));
                         /***************/
                         //generating application.properties.tpl.tpl
        ModifiedConfigFileGeneration clientconf =new ModifiedConfigFileGeneration(
                microserviceName,
                Main.getPath2(),
                microservice.getPort(),
                "ambassador/client");

        clientconf.setClientInfo(clientinfo.getAmbassadorMap());
        builder.setContent(clientconf);


        builder.setContent(new ClientGeneration(
                microserviceName,
                Main.getPath2(),
                "ambassador/client",
                "/src/main/java/microservice/Ambassador/client/demo/API", builder.getPackageName(),microservice.getId()));

        return builder;
    }
    else if (id ==1){

            Builder builder = new Builder("microservice." + "Ambassador.ambassador" + ".demo.API");

        builder.setContent(new POMGenerator(
                microserviceName, Main.getPath2(), "ambassador/ambassador"));

            ModifiedConfigFileGeneration ambassadorconf =new ModifiedConfigFileGeneration(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "ambassador/ambassador");

        ambassadorconf.setClientInfo(ambassadorInfo.getExternalServiceMap());
            builder.setContent(ambassadorconf);

            builder.setContent(new AmbassadorGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "ambassador/ambassador",
                    "/src/main/java/microservice/Ambassador/ambassador/demo/API", builder.getPackageName(),microservice.getId()));

            return builder;
        }
    else if (id ==2){
        //External service
        Builder builder = new Builder("microservice." + "Ambassador.externalService" + ".demo.API");

        builder.setContent(new POMGenerator(
                microserviceName, Main.getPath2(), "ambassador/external-service"));
        /***************/
        builder.setContent(new ConfigFileGenerator(
                microserviceName,
                Main.getPath2(),
                microservice.getPort(),
                "ambassador/external-service"));
        /***************/

        builder.setContent(new ExternalServiceGeneration(
                microserviceName,
                Main.getPath2(),
                "ambassador/external-service",
                "/src/main/java/microservice/Ambassador/externalService/demo/API", builder.getPackageName()));

        return builder;
    }
    else {return null;}
}
}
