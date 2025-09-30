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

public class EventSourcingStrategy extends Strategy {
    public static int clientCounter = 0;
    public static int eventManagerCounter = 0;
    public static int eventDatabaseManagerCounter = 0;
    public static int regularDatabaseManagerCounter = 0;

    String clientRole = "Client";
    String eventManagerRole = "EventManager";
    String eventDatabaseManagerRole = "EventDatabaseManager";
    String regularDatabaseManagerRole = "RegularDatabaseManager"; //Worker
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

        String microserviceName;
        microserviceName = eventManagerRole + eventManagerCounter++;
        Microservice eventManager = null;
        try {
            eventManager = new Microservice(
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/create_event"),
                            new Pair<>(Microservice.ConnectionType.DELETE, "/delete_event"),
                            new Pair<>(Microservice.ConnectionType.POST, "/edit_event"),
                            new Pair<>(Microservice.ConnectionType.GET, "/read_data")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.EventSourcing.toString(), Microservice.Role.EventManager.toString()),
                    new EventSourcingStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(eventManager);

        microserviceName = eventDatabaseManagerRole + eventDatabaseManagerCounter++;
        Microservice eventDatabaseManager = null;
        try {
            eventDatabaseManager = new Microservice(
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/append_event"),
                            new Pair<>(Microservice.ConnectionType.GET, "/rollback"),
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.EventSourcing.toString(), Microservice.Role.EventDatabaseManager.toString()),
                    new EventSourcingStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(eventDatabaseManager);
        eventManager.setConnections(new Pair<>(Microservice.ConnectionType.POST, eventDatabaseManager.getURI()));
        eventManager.setUsageMemory();
///////////////////////////////////////////////////////////////////////////////////////
        microserviceName = regularDatabaseManagerRole + regularDatabaseManagerCounter++;
        Microservice regularDatabaseManager = null;
        try {
            regularDatabaseManager = new Microservice(
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/apply_event"),
                            new Pair<>(Microservice.ConnectionType.GET, "/get_data"),
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.EventSourcing.toString(), Microservice.Role.Worker.toString()),
                    new EventSourcingStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(regularDatabaseManager);
        eventDatabaseManager.setConnections(new Pair<>(Microservice.ConnectionType.POST, regularDatabaseManager.getURI()));
        eventManager.setUsageMemory();
        regularDatabaseManager.setConnections(new Pair<>(Microservice.ConnectionType.GET, eventDatabaseManager.getURI()));
        regularDatabaseManager.setUsageMemory();
        eventManager.setConnections(new Pair<>(Microservice.ConnectionType.GET, regularDatabaseManager.getURI()));
        eventManager.setUsageMemory();
/////////////////////////////////////////////////////////////////////////////////////////////////////
        int clientNumber = random.nextInt(6) + 3;
        Microservice client = null;
        for (int i = 0; i < clientNumber; i++) {
            microserviceName = clientRole + clientCounter++;

            try {
                client = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{},
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.EventSourcing.toString(), Microservice.Role.Client.toString()),
                        new EventSourcingStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(client);
            client.setConnections(new Pair<>(Microservice.ConnectionType.POST, eventManager.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, eventManager.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.PUT, eventManager.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, eventManager.getURI()));
            client.setUsageMemory();
        }
        client.setUsageCPU();
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
            case "EventManager":
                builder = eventManagerClass(microserviceName, connections);
                setMethodsEventManager(builder);
                break;
            case "EventDatabaseManager":
                builder = eventDatabaseManagerClass(microserviceName, connections);
                setMethodsEventDatabaseManager(builder);
                break;
            case "Worker":
                builder = regularDatabaseManagerClass(microserviceName, connections);
                setMethodsRegularDatabaseManager(builder);
                break;
        }
        return builder;
    }

    private Builder clientClass() {
        return new Builder("microservice." + "eventsourcing" + ".demo.API")
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
                                        "0",
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

    private Builder eventManagerClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "eventsourcing" + ".demo.API")
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
                                        new Pair<>(Constants.VariableType.AddNewType("Map<String,String>"), "events"),
                                        "",
                                        null,
                                        false,
                                        false)
                        )
                );
    }

    private void setMethodsEventManager(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "readData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "/read_data")
                },
                Constants.Keywords.NULL.toString(),
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "createEvent"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "id"),
                        new Pair<>(Constants.VariableTypeRequestBody.String, "data")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/create_event")
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "deleteEvent"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.DeleteMapping, "/delete_event")
                },
                Constants.Keywords.NULL.toString(),
                Deletelist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "editEvent"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "id"),
                        new Pair<>(Constants.VariableTypeRequestBody.String, "modifications")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PutMapping, "/edit_event")
                },
                Constants.Keywords.NULL.toString(),
                Putlist
        );
    }

    private Builder eventDatabaseManagerClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "eventsourcing" + ".demo.API")
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
                                        new Pair<>(Constants.VariableType.AddNewType("ArrayList<String>"), "database"),
                                        "",
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.AddNewType("Map<String,String>"), "events"),
                                        "",
                                        null,
                                        false,
                                        false)
                        )
                );
    }

    private void setMethodsEventDatabaseManager(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "rollback"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "/rollback")
                },
                Constants.Keywords.NULL.toString(),
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "appendEvent"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "id"),
                        new Pair<>(Constants.VariableTypeRequestBody.String, "data")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/append_event")
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
    }

    private Builder regularDatabaseManagerClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "eventsourcing" + ".demo.API")
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
                                        new Pair<>(Constants.VariableType.AddNewType("ArrayList<String>"), "database"),
                                        "",
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>(Constants.VariableType.AddNewType("Map<String,String>"), "events"),
                                        "",
                                        null,
                                        false,
                                        false)
                        )
                );
    }

    private void setMethodsRegularDatabaseManager(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "getData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "/get_data")
                },
                Constants.Keywords.NULL.toString(),
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "receiveRequest"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "id"),
                        new Pair<>(Constants.VariableTypeRequestBody.String, "data")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/apply_event")
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
    }

}
