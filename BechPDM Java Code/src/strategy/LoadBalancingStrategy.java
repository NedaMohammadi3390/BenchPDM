package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.BodyGenerator;
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

public class LoadBalancingStrategy extends Strategy {
    public static int headCounter = 0;
    public static int workerCounter = 0;
    String headRole = "Head";
    String workerRole = "Worker";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    public  int i = 1;
    public int j=1;
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
        int workerNumber = random.nextInt(5) + 2;
        String microserviceName = headRole + headCounter++;

        Microservice head = null;
        try {
            head = new Microservice(
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{},
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.Consolidation.toString(), Microservice.Role.Head.toString()),
                    new LoadBalancingStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        matrices.add(head);

        for (int i = 0; i < workerNumber; i++) {
            microserviceName = workerRole + workerCounter++;
            Microservice worker = null;
            try {
                worker = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, ""),
                                new Pair<>(Microservice.ConnectionType.PUT, "/properties"),
                                new Pair<>(Microservice.ConnectionType.GET, "/process")
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.Consolidation.toString(), Microservice.Role.Worker.toString()),
                        new LoadBalancingStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            head.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker.getURI()));
            head.setConnections(new Pair<>(Microservice.ConnectionType.PUT, worker.getURI()));
            head.setConnections(new Pair<>(Microservice.ConnectionType.GET, worker.getURI()));
            head.setUsageMemory();
            matrices.add(worker);
        }
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
            case "Head":
                builder = headClass(microserviceName);
                break;
            case "Worker":
                builder = workerClass(microserviceName, connections);
                setMethods(builder);
                break;
        }
        return builder;
    }

    private void setMethods(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("ArrayList<Pair<String, Integer>>"), "getProperties"),
                null,
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "/properties")
                },
                "Arraylist",
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "getProcess"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("int").toLowerCase(), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PutMapping, "/process")
                },
                "Object",
                Putlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair<>(Constants.ReturnType.BOOLEAN, "receiveProcess"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.Object, "object")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "")
                },
               "boolean",
                Postlist
        );
    }

    private Builder headClass(String microserviceName) {
        return new Builder("microservice." + "consolidation" + ".demo.API")
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
                                new BodyGenerator(
                                        new Pair[]{ new Pair<>(Constants.Annotations.Autowired, "")},
                                        new ArrayList<Pair<String, String>>() {{
                                            add(new Pair<>("RestTemplate restTemplate", "RestTemplate"));
                                        }},
                                        list

                                )
                        )
                );
    }

    private Builder workerClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "consolidation" + ".demo.API")
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
                                                null)
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
                        )
                );
    }

}
