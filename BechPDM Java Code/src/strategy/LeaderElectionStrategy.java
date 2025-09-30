package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.ClassGenerator;
import content_generation.generator.ConstructorGenerator;
import content_generation.generator.VariableGenerator;
import data_structure.Microservice;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeaderElectionStrategy extends Strategy {
    public static int leaderCounter = 0;
    public static int workerCounter = 0;
    String leaderRole = "Leader";
    String workerRole = "Worker";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    public  int i = 1;
    public int j=1;
    ArrayList<Pair<Pair<String, String>, String>> Postlist2;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();


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
            }//            Microservice.ConnectionType type = pair.getKey();
        }

    }


    @Override
    public ArrayList<Microservice> matrixFiller( ) {
        ArrayList<Microservice> matrices = new ArrayList<>();

        String microserviceName;
        microserviceName = leaderRole + leaderCounter++;
        Microservice leader = null;
        try {
            leader = new Microservice(
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/id_retrieval"),
                            new Pair<>(Microservice.ConnectionType.POST, "/set_leader"),
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


        int workerNumber = random.nextInt(5) + 1;
        for (int i = 0; i < workerNumber; i++) {
            microserviceName = workerRole + workerCounter++;
            Microservice worker = null;
            try {
                worker = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, "/id_retrieval"),
                                new Pair<>(Microservice.ConnectionType.POST, "/set_leader"),
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.LeaderElection.toString(), Microservice.Role.Worker.toString()),
                        new LeaderElectionStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            leader.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker.getURI()));
//            worker.setConnections(new Pair<>(Microservice.ConnectionType.POST, leader.getURI()));
            matrices.add(worker);
        }

        int totalMicros = matrices.size();
        for(int i=0;i<totalMicros;i++){
            for(int j=0;j<totalMicros;j++){
                if(i!=j){
                    matrices.get(i).setConnections(new Pair<>(Microservice.ConnectionType.POST, matrices.get(j).getURI()));
                    matrices.get(i).setUsageMemory();
                }
//                if(i == 0){
//                    matrices.get(i).setConnections(new Pair<>(Microservice.ConnectionType.POST, matrices.get(j).getURI()));
//                }
            }
        }
        leader.setUsageCPU();
        return matrices;
    }

    @Override
    public Builder fileFiller(String role, String microserviceName, String[] connections, Pair<Microservice.ConnectionType, String>[] connectionTypes) {
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

        Builder builder = new Builder();
        switch (role) {
            case "Leader":
                builder = leaderClass(microserviceName, connections);
                setMethodsLeader(builder);
                break;
            case "Worker":
                builder = workerClass(microserviceName, connections);
                setMethodsWorker(builder);
                break;
        }
        return builder;
    }

    private Builder leaderClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "leaderElection" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                "MicroserviceController",
                                null,
                                "",
                                new ConstructorGenerator[]{
                                        new ConstructorGenerator(
                                                Constants.AccessLevel.PUBLIC,
                                                "MicroserviceController",
                                                null,
                                                null,
                                                null,
                                                new ArrayList<Pair<String, String>>() {{
                                                    add(new Pair<>("connections", "ArrayList<String>"));
                                                }})
                                },
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.RequestMapping, "api/v1/" + microserviceName),
                                        new Pair<>(Constants.Annotations.RestController, "")
                                }
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.INT, "id"),
                                        "",
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.AddNewType("ArrayList<String>"), "connections"),
                                        "",
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.BOOLEAN, "isLeader"),
                                        "true",
                                        null,
                                        false,
                                        false)
                        )
                );
    }

    private void setMethodsLeader(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "setLeader"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("INT"), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/set_leader")
                },
                Constants.Keywords.NULL.toString(),
                 Postlist

        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.BOOLEAN, "idRetrieval"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("INT"), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/id_retrieval")
                },
                Constants.Keywords.FALSE.toString(),
                 Postlist
        );
    }


    private Builder workerClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "leaderElection" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                "MicroserviceController",
                                null,
                                "",
                                new ConstructorGenerator[]{
                                        new ConstructorGenerator(
                                                Constants.AccessLevel.PUBLIC,
                                                "MicroserviceController",
                                                null,
                                                null,
                                                null,
                                                new ArrayList<Pair<String, String>>() {{
                                                    add(new Pair<>("connections", "ArrayList<String>"));
                                                }})
                                },
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.RequestMapping, "api/v1/" + microserviceName),
                                        new Pair<>(Constants.Annotations.RestController, "")
                                }
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.INT, "id"),
                                        "",
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.AddNewType("ArrayList<String>"), "connections"),
                                        "",
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.BOOLEAN, "isLeader"),
                                        "false",
                                        null,
                                        false,
                                        false)
                        )
                );
    }

    private void setMethodsWorker(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "setLeader"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("INT"), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/set_leader")
                },
                Constants.Keywords.NULL.toString(),
                 Postlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.BOOLEAN, "idRetrieval"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("INT"), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/id_retrieval")
                },
                Constants.Keywords.FALSE.toString(),
             Postlist
        );
    }


}
