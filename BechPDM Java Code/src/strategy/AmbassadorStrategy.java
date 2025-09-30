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

public class AmbassadorStrategy extends Strategy {
    public static int clientCounter = 0;
    public static int clientHostId = -1;
    public static int ambassadorCounter = 0;
    public static int workerCounter = 0;
    String clientRole = "Client";
    String ambassadorRole = "Ambassador";
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
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();

        String microserviceName;
        microserviceName = ambassadorRole + ambassadorCounter++;
        Microservice ambassador = null;
        try {
            ambassador = new Microservice(
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

        int clientNumber = random.nextInt(11) + 5;
        for (int i = 0; i < clientNumber; i++) {
            microserviceName = clientRole + clientCounter++;
            Microservice client = null;
            try {
                client = new Microservice(
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
            client.setConnections(new Pair<>(Microservice.ConnectionType.POST, ambassador.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, ambassador.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.PUT, ambassador.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, ambassador.getURI()));
            client.setUsageMemory();
        }

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
                                new Pair<>(Microservice.ConnectionType.POST, ""),
                                new Pair<>(Microservice.ConnectionType.GET, ""),
                                new Pair<>(Microservice.ConnectionType.PUT, ""),
                                new Pair<>(Microservice.ConnectionType.DELETE, "")
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.Ambassador.toString(), Microservice.Role.Worker.toString()),
                        new AmbassadorStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(worker);
            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker.getURI()));
            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.GET, worker.getURI()));
            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.PUT, worker.getURI()));
            ambassador.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, worker.getURI()));
            ambassador.setUsageMemory();
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
            case "Client":
                builder = clientClass();
                break;
            case "Ambassador":
                builder = ambassadorClass(microserviceName, connections);
                setMethods(builder);
                clientHostId++;
                break;
            case "Worker":
                builder = workerClass(microserviceName, connections);
                setMethods(builder);
                break;
        }
        return builder;
    }

    private Builder clientClass() {
        return new Builder("microservice." + "ambassador" + ".demo.API")
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
                                                    add(new Pair<>("connection", "String"));
                                                }})
                                },
                                new Pair[]{}
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
                                        true,
                                        true,
                                        new Pair<>(Constants.VariableType.INT, "hostId"),
                                        String.valueOf(clientHostId),
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.AddNewType("String"), "connection"),
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

    private Builder ambassadorClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "ambassador" + ".demo.API")
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
                        )
                );
    }

    private void setMethods(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "getData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("int"), "identifier")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "")
                },
                Constants.Keywords.NULL.toString(),
                list
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.BOOLEAN, "setData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("Object[]"), "objects")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "")
                },
                Constants.Keywords.FALSE.toString(),
                Postlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.BOOLEAN, "putData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.Object, "object")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PutMapping, "")
                },
                Constants.Keywords.FALSE.toString(),
                Putlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.BOOLEAN, "deleteData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.Object, "object")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.DeleteMapping, "")
                },
                Constants.Keywords.FALSE.toString(),
                Deletelist
        );
    }

    private Builder workerClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "ambassador" + ".demo.API")
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
