package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.*;
import content_generation.generator.CustomizedCodeFileGenerator.priorityQueu.ClientGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.priorityQueu.DispatcherGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.priorityQueu.ExternalGeneration;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.*;

public class PriorityQueueStrategy extends Strategy {
    public static int ClientServiceCounter = 0;
    public static int DispatcherServiceCounter = 0;
    public static int ExternalServiceCounter = 0;
    String clientRole = "ClientService";
    String dispatcherRole = "DispatcherService";
    String externalRole = "ExternalService";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    public  int i = 1;
    public int j=1;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();

    private static List<Integer> providersPorts = new ArrayList<>();
    private static int dispatchPort;

    public void initialConnected(String[] connection, Microservice.ConnectionType connectionType) {

        String  strcon;

        for (String str : connection) {

            if (connectionType!= null){
                strcon = str + "/" + connectionType.toString().toLowerCase() + ","
                        + "HTTP." + connectionType + "," + "entity" + "," + "String.class";

                if (connectionType.equals(Microservice.ConnectionType.GET)){
                    Getlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));}

                else if (connectionType.equals(Microservice.ConnectionType.POST)){
                    Postlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));}

                else if (connectionType.equals(Microservice.ConnectionType.PUT)){
                    Putlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));}

                else  if (connectionType.equals(Microservice.ConnectionType.DELETE)){
                    Deletelist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));}

                j=j+1;
            } else {
                strcon = str;
                list.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + i, "restTemplate.exchange"), strcon));
                i = i + 1;
            }
        }
    }
    @Override
    public ArrayList<Microservice> matrixFiller( ) {
        ArrayList<Microservice> matrices = new ArrayList<>();
        String microserviceName;
        String URI;
        int port;
        Microservice dispatcher = null;
        microserviceName = dispatcherRole + DispatcherServiceCounter++;

        URI = URIGenerator(microserviceName);
        port = getPort();
        dispatchPort=port;
//        providersPorts.add(port);
        try {
            dispatcher = new Microservice(
                    port,
                    id++,
                    microserviceName,
                    URI,
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, ""),

                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.PriorityQueue.toString(), Microservice.Role.DispatcherService.toString()),
                    new PriorityQueueStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(dispatcher);

        ////////////////////////////////////////////////
        int clientNumber = random.nextInt(5) + 2;
        for (int i = 0; i < clientNumber; i++) {
            microserviceName = clientRole + ClientServiceCounter++;
            Microservice client = null;
            URI = URIGenerator(microserviceName);
            port = getPort();
            try {
                client = new Microservice(
                        port,
                        id++,
                        microserviceName,
                        URI,
                        new Pair[]{},
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.PriorityQueue.toString(), Microservice.Role.ClientService.toString()),
                        new PriorityQueueStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(client);


            client.setConnections(new Pair<>(Microservice.ConnectionType.POST, dispatcher.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.PUT, dispatcher.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, dispatcher.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, dispatcher.getURI()));
            client.setUsageMemory();

        }
            int externalNumber = random.nextInt(3) + 1;
            for (int ii = 0; ii < externalNumber; ii++) {
        microserviceName = externalRole + ExternalServiceCounter++;
        Microservice external = null;
        URI = URIGenerator(microserviceName);
        port = getPort();
        providersPorts.add(port);
        try {
            external = new Microservice(
                    port,
                    id++,
                    microserviceName,
                    URI,
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, ""),

                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.PriorityQueue.toString(), Microservice.Role.ExternalService.toString()),
                    new PriorityQueueStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(external);
        dispatcher.setConnections(new Pair<>(Microservice.ConnectionType.POST, external.getURI()));
                dispatcher.setConnections(new Pair<>(Microservice.ConnectionType.PUT, external.getURI()));
                dispatcher.setConnections(new Pair<>(Microservice.ConnectionType.GET, external.getURI()));
                dispatcher.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, external.getURI()));
        dispatcher.setUsageMemory();

        dispatcher.setUsageCPU();}
        return matrices;

    }

    @Override
    public Builder[] fileFiller(Microservice microservice,
                                String role,
                                String microserviceName,
                                String[] connections,
                                Pair<Microservice.ConnectionType, String>[] connectionTypes)
    {
        String[] uniqueArray = Arrays.stream(connections)
                .distinct()
                .toArray(String[]::new);

        Set<Pair<Microservice.ConnectionType, String>> uniqueSet = new HashSet<>();
        for (Pair<Microservice.ConnectionType, String> entry : connectionTypes) {
            uniqueSet.add(entry);
        }
        if (!uniqueSet.isEmpty()){
            for (Pair<Microservice.ConnectionType, String> up : uniqueSet) {
                initialConnected(uniqueArray, up.getKey());
            }
        } else{ initialConnected(uniqueArray,null);
        }

        int n = 1; // تعداد Builderها
        Builder[] builders = new Builder[n];
        switch (role) {
            case "ClientService":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 0);
                break;

            case "DispatcherService":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 1);
                break;
//
            case "ExternalService":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 2);
                break;
        }
        return builders;
    }
    /*******************************************************************
     * ******************************************************************
     * *******************************************************************/
    private Builder GenerationClass(Microservice microservice,
                                    String microserviceName,
                                    String[] connections, int id)
    {
        if (id == 0) {
            Builder builder = new Builder("microservice." + "priorityQueue" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(),"priority-queue"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "priority-queue"));

            builder.setContent(new ClientGeneration(
                    microserviceName,
                    microserviceName+"Scheduler",
                    Main.getPath2(),
                    "priority-queue",
                    "/src/main/java/microservice/priorityQueue/demo/API", builder.getPackageName(),dispatchPort));

            return builder;}

        else if (id==1){
            Builder builder = new Builder("microservice." + "priorityQueue" + ".demo.API");
            builder.setContent(new POMGenerator(
                            microserviceName, Main.getPath2(),"priority-queue"));
            builder.setContent(new ConfigFileGenerator(
                            microserviceName,
                            Main.getPath2(),
                            microservice.getPort(),
                            "priority-queue"));
            builder.setContent(
                            new DispatcherGeneration(
                                    microserviceName,
                                    microserviceName+"Listener",
                                    Main.getPath2(),
                                    "priority-queue",
                                    providersPorts,
                                    "/src/main/java/microservice/priorityQueue/demo/API",builder.getPackageName()));
            return builder;
        }
        else if (id==2){
            Builder builder = new Builder("microservice." + "priorityQueue" + ".demo.API")
            .setContent(new POMGenerator(
                            microserviceName, Main.getPath2(),"priority-queue"));
            builder.setContent(new ConfigFileGenerator(
                            microserviceName,
                            Main.getPath2(),
                            microservice.getPort(),
                            "priority-queue"));
            builder.setContent(
                            new ExternalGeneration(
                                    microserviceName,
                                    Main.getPath2(),
                                    "priority-queue",
                                    "/src/main/java/microservice/priorityQueue/demo/API",builder.getPackageName()));
            return builder;
        }
        else {return null;}
    }

}
