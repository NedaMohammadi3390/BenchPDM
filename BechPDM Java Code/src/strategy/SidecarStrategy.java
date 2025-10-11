package strategy;

import content_generation.Builder;
import content_generation.generator.*;
import content_generation.generator.CustomizedCodeFileGenerator.sidecar.*;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.*;

public class SidecarStrategy extends Strategy {
    public static int externalServiceCounter = 0;
    public static int sidecarCounter = 0;
    public static int mainServiceCounter = 0;
    public static int zuulCounter = 0;
    public static int eurekaCounter = 0;
    String externalServiceRole = "ExternalService";
    String sidecarRole = "Sidecar";
    String mainServiceRole = "MainService";
    String zuulRole = "ApiGateway";
    String eurekaRole = "ServiceRegistry";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;


    //**********************************************************
    @Override
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();
        int externalServiceNumber = random.nextInt(3) + 3;
        String microserviceName;

        //sidecar service
        Microservice sidecar = null;
            microserviceName = sidecarRole + sidecarCounter++;
            try {
                sidecar = new Microservice(
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
                        new Pair<>(Microservice.Pattern.Sidecar.toString(), Microservice.Role.Sidecar.toString()),
                        new SidecarStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(sidecar);

            // external service
        for (int i=1; i<externalServiceNumber; i++){
            Microservice external = null;
            microserviceName = externalServiceRole + externalServiceCounter++;
            try {
                external = new Microservice(
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
                        new Pair<>(Microservice.Pattern.Sidecar.toString(), Microservice.Role.ExternalService.toString()),
                        new SidecarStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(external);
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.POST, external.getURI()));
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.PUT, external.getURI()));
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.GET, external.getURI()));
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, external.getURI()));

            external.setConnections(new Pair<>(Microservice.ConnectionType.POST, sidecar.getURI()));
            external.setConnections(new Pair<>(Microservice.ConnectionType.PUT, sidecar.getURI()));
            external.setConnections(new Pair<>(Microservice.ConnectionType.GET, sidecar.getURI()));
            external.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, sidecar.getURI()));
        }
        //*******************************************************
        //mainService
        int mainServiceRoleNumber = random.nextInt(4) + 2;
        for (int ii=1; ii<mainServiceRoleNumber; ii++){
            Microservice mainS = null;
            microserviceName = mainServiceRole + mainServiceCounter++;
            try {
                mainS = new Microservice(
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
                        new Pair<>(Microservice.Pattern.Sidecar.toString(), Microservice.Role.MainService.toString()),
                        new SidecarStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(mainS);
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.POST, mainS.getURI()));
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.PUT, mainS.getURI()));
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.GET, mainS.getURI()));
            sidecar.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, mainS.getURI()));

            mainS.setConnections(new Pair<>(Microservice.ConnectionType.POST, sidecar.getURI()));
            mainS.setConnections(new Pair<>(Microservice.ConnectionType.PUT, sidecar.getURI()));
            mainS.setConnections(new Pair<>(Microservice.ConnectionType.GET, sidecar.getURI()));
            mainS.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, sidecar.getURI()));

        }
        //zuul service
        Microservice zuul = null;
        microserviceName = zuulRole + zuulCounter++;
        try {
            zuul = new Microservice(
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
                    new Pair<>(Microservice.Pattern.Sidecar.toString(), Microservice.Role.ApiGateway.toString()),
                    new SidecarStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(zuul);

        //eureka Service
        Microservice eureka = null;
        microserviceName = eurekaRole + eurekaCounter++;
        try {
            eureka = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{},
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.Sidecar.toString(), Microservice.Role.ServiceRegistry.toString()),
                    new SidecarStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(eureka);
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
            case "ExternalService":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 0);
                break;

            case "Sidecar":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 1);
                break;

            case "MainService":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 2);
                break;

            case "ApiGateway":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 3);
                break;

            case "ServiceRegistry":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 4);
                break;
        }
        return builders;
    }

    /*******************************************************************
     * ******************************************************************
     * *******************************************************************/
    private Builder GenerationClass(Microservice microservice,
                                    String microserviceName,
                                    String[] connections, int id) {
        //ExternalService service
        if (id == 0) {
            Builder builder = new Builder("microservice." + "sidecar.externalService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "sidecar/external-service"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "sidecar/external-service"));

            builder.setContent(new ExternalServiceGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "sidecar/external-service",
                    "/src/main/java/microservice/sidecar/externalService/demo/API", builder.getPackageName()));

            return builder;
        }
        //Sidecar service
        if (id == 1) {
            Builder builder = new Builder("microservice." + "sidecar"+".SidecarService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "sidecar/sid"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "sidecar/sid"));

            builder.setContent(new SidecarGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "sidecar/sid",
                    "/src/main/java/microservice/sidecar/SidecarService/demo/API",
                    builder.getPackageName()));

            return builder;
        }
        // MainService
        if (id == 2) {
            Builder builder = new Builder("microservice." + "sidecar.MainService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "sidecar/main-service"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "sidecar/main-service"));

            builder.setContent(new MainServiceGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "sidecar/main-service",
                    "/src/main/java/microservice/sidecar/MainService/demo/API",
                    builder.getPackageName()));

            return builder;
        }
        //ApiGateway
        if (id == 3) {
            Builder builder = new Builder("microservice." + "sidecar.zuulService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "sidecar/zuul"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "sidecar/zuul"));

            builder.setContent(new ZuulGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "sidecar/zuul",
                    "/src/main/java/microservice/sidecar/zuulService/demo/API",
                    builder.getPackageName()));

            return builder;
        }
        //ServiceRegistry
        if (id == 4) {
            Builder builder = new Builder("microservice." + "sidecar.EurekaService" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "sidecar/eureka"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "sidecar/eureka"));

            builder.setContent(new EurekaGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "sidecar/eureka",
                    "/src/main/java/microservice/sidecar/EurekaService/demo/API",
                    builder.getPackageName()));

            return builder;
        }
        else {
            return null;
        }

    }
}
