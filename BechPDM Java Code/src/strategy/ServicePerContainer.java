package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.*;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;
import content_generation.generator.CustomizedCodeFileGenerator.DockerfileGenerator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ServicePerContainer extends Strategy {
    public static int clientUserCounter = 0;
    public static int clientAdminCounter = 0;
    public static int storageCounter = 0;
    String clientRoleUser = "Client";
    String clientRoleAdmin = "Admin";
    String storageRole = "Storage";
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
        int storageNumber = random.nextInt(5) + 1;

        String microserviceName;
        ArrayList<Microservice> localStorages = new ArrayList<>();
        ArrayList<Microservice> localAdmins = new ArrayList<>();
        ArrayList<Microservice> localClients = new ArrayList<>();
        Microservice client = null;
        for (int i = 0; i < storageNumber; i++) {
            microserviceName = storageRole + storageCounter++;
            Microservice storage = null;
            try {
                storage = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, ""),
                                new Pair<>(Microservice.ConnectionType.GET, ""),
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.StaticContentHost.toString(), Microservice.Role.Storage.toString()),
                        new ServicePerContainer()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            localStorages.add(storage);
            matrices.add(storage);

            int adminNumber = random.nextInt(3) + 1;
            for (int j = 0; j < adminNumber; j++) {
                microserviceName = clientRoleAdmin + clientAdminCounter++;
                Microservice admin = null;
                try {
                    admin = new Microservice(
                            getPort(),
                            id++,
                            microserviceName,
                            URIGenerator(microserviceName),
                            new Pair[]{},
                            false,
                            creationTime(),
                            new Pair<>(Microservice.Pattern.StaticContentHost.toString(), Microservice.Role.Client.toString()),
                            new ServicePerContainer()
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                localAdmins.add(admin);
                matrices.add(admin);
                admin.setConnections(new Pair<>(Microservice.ConnectionType.POST, storage.getURI()));
            }

            int clientNumber = random.nextInt(3) + 1;
            for (int j = 0; j < clientNumber; j++) {
                microserviceName = clientRoleUser + clientUserCounter++;

                try {
                    client = new Microservice(
                            getPort(),
                            id++,
                            microserviceName,
                            URIGenerator(microserviceName),
                            new Pair[]{},
                            false,
                            creationTime(),
                            new Pair<>(Microservice.Pattern.StaticContentHost.toString(), Microservice.Role.Client.toString()),
                            new ServicePerContainer()
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                localClients.add(client);
                matrices.add(client);
                client.setConnections(new Pair<>(Microservice.ConnectionType.GET, storage.getURI()));
                client.setUsageMemory();
            }
        }
        distributeClients(localStorages, localAdmins, localClients);
        client.setUsageCPU();
        return matrices;
    }

    private void distributeClients(ArrayList<Microservice> localStorages, ArrayList<Microservice> localAdmins, ArrayList<Microservice> localClients) {
        for (Microservice admin :
                localAdmins) {
            ArrayList<Pair<Microservice.ConnectionType, String>> connectionsPair = admin.getConnectionsPair();
            storageLoop:
            for (Microservice storage :
                    localStorages) {
                for (Pair p :
                        connectionsPair) {
                    if (p.getValue().equals(storage.getURI()))
                        continue storageLoop;
                }
                admin.setConnections(new Pair<>(Microservice.ConnectionType.POST, storage.getURI()));
            }
        }
        for (Microservice client :
                localClients) {
            ArrayList<Pair<Microservice.ConnectionType, String>> connectionsPair = client.getConnectionsPair();
            storageLoop:
            for (Microservice storage :
                    localStorages) {
                for (Pair p :
                        connectionsPair) {
                    if (p.getValue().equals(storage.getURI()))
                        continue storageLoop;
                }
                client.setConnections(new Pair<>(Microservice.ConnectionType.GET, storage.getURI()));
            }
        }
    }

    @Override
    public Builder[] fileFiller(Microservice microservice,String role, String microserviceName, String[] connections, Pair<Microservice.ConnectionType, String>[] connectionTypes) {
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

        int n = 1;
        Builder[] builder = new Builder[n];
        switch (role) {
            case "Client":
                builder[0] = clientClass(microserviceName);
                break;
            case "Storage":
                builder[0] = storageClass(microserviceName, connections);
                setMethods(builder[0]);
                break;
        }
        return builder;
    }

    private Builder clientClass(String microserviceName) {
        Builder builder = new Builder("microservice." + "staticcontenthost" + ".demo.API");

        builder.setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
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
                                new Pair[]{},new String[]{"import org.springframework.web.bind.annotation.PostMapping;"}
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
        builder.setContent(new DockerfileGenerator(microserviceName, Main.getPath2(), "ServicePerContainer"));
        builder.setContent(new POMGenerator(
                microserviceName, Main.getPath2(), "ServicePerContainer"));
        return builder;
    }

    private Builder storageClass(String microserviceName, String[] connections) {
        Builder builder = new Builder("microservice." + "staticcontenthost" + ".demo.API");

                builder.setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
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
                                },new String[]{"import org.springframework.web.bind.annotation.PostMapping;"}
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
                builder.setContent(new DockerfileGenerator(microserviceName, Main.getPath2(), "ServicePerContainer"));
        builder.setContent(new POMGenerator(
                microserviceName, Main.getPath2(), "ServicePerContainer"));
     return builder;
    }

    private void setMethods(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "returnData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("int",""), "identifier")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "")
                },
                Constants.Keywords.NULL.toString(),
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.BOOLEAN, "putData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("Object[]",""), "objects")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "")
                },
                Constants.Keywords.FALSE.toString(),
                Postlist
        );
    }

}
