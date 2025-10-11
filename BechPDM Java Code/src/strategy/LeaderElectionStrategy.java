package strategy;

import content_generation.Builder;
import content_generation.generator.ConfigFileGenerator;
import content_generation.generator.CustomizedCodeFileGenerator.leaderElection.ClientGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.leaderElection.LeaderGeneration;
import content_generation.generator.POMGenerator;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;

public class LeaderElectionStrategy extends Strategy {
    public static int leaderCounter = 0;
    public static int clientCounter = 0;
    String leaderRole = "Leader";
    String clientRole = "Client";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    static int leaderPort;
    static String leaderName;


    @Override
    public ArrayList<Microservice> matrixFiller( ) {
        ArrayList<Microservice> matrices = new ArrayList<>();

        String microserviceName;
        microserviceName = leaderRole + leaderCounter++;
        leaderPort =getPort();
         leaderName=microserviceName;
        Microservice leader = null;
        try {
            leader = new Microservice(
                    leaderPort,
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.GET, ""),
                            new Pair<>(Microservice.ConnectionType.PUT, ""),
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.LeaderElection.toString(), Microservice.Role.Leader.toString()),
                    new LeaderElectionStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(leader);


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
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.GET, ""),
                                new Pair<>(Microservice.ConnectionType.PUT, ""),
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.LeaderElection.toString(), Microservice.Role.Client.toString()),
                        new LeaderElectionStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, leader.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.PUT, leader.getURI()));
            matrices.add(client);
        }
        leader.setUsageCPU();
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
            case "Leader":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 0);
                break;

            case "Client":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 1);
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
        //Leader service
        if (id == 0) {
            Builder builder = new Builder("microservice." + "leaderElection.leader" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "leader-election/leader"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "leader-election/leader"));

            builder.setContent(new LeaderGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "leader-election/leader",
                    "/src/main/java/microservice/leaderElection/leader/demo/API",
                    builder.getPackageName()));

            return builder;
        }
        //Client service
        if (id == 1) {
            Builder builder = new Builder("microservice." + "leaderElection.client" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "leader-election/client"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "leader-election/client"));

            builder.setContent(new ClientGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "leader-election/client",
                    "/src/main/java/microservice/leaderElection/client/demo/API",
                    builder.getPackageName(),leaderPort,leaderName));

            return builder;
        }
        else {return null;}
    }
}