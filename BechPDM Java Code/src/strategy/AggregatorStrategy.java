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
public class AggregatorStrategy extends Strategy {
    public static int aggregatorCounter = 0;
    public static int workerCounter = 0;
    String aggregatorRole = "Aggregator";
    String workerRole = "Worker";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    public int i = 1;
    public int j = 1;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();


    public void initialConnected(String[] connection, Microservice.ConnectionType connectionType) {

        String strcon;

        for (String str : connection) {

            if (connectionType != null) {
                strcon = str + "/" + connectionType.toString().toLowerCase() + ","
                        + "HTTP." + connectionType + "," + "entity" + "," + "String.class";

                if (connectionType.equals(Microservice.ConnectionType.GET)) {
                    Getlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.POST)) {
                    Postlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.PUT)) {
                    Putlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.DELETE)) {
                    Deletelist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "restTemplate.exchange"), strcon));
                }

                j = j + 1;
            } else {
                strcon = str;
                list.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + i, "restTemplate.exchange"), strcon));
                i = i + 1;
            }
        }
    }

    @Override
    public ArrayList<Microservice> matrixFiller() {

        ArrayList<Microservice> matrices = new ArrayList<>();
        int workerNumber = random.nextInt(5) + 2;

        String microserviceName = aggregatorRole + aggregatorCounter++;

        Microservice aggregator = null;
        try {
            aggregator = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{},
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.Aggregator.toString(), Microservice.Role.Aggregator.toString()),
                    new AggregatorStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(aggregator);

        for (int i = 0; i < workerNumber; i++) {
            microserviceName = workerRole + workerCounter++;
            int linksNumber = random.nextInt(2) + 1;

            Pair[] links = new Pair[linksNumber * 4];
            int jj = 0;
            while (jj < links.length) {

                links[jj] = new Pair(Microservice.ConnectionType.GET, String.valueOf(jj));
                links[jj + 1] = new Pair(Microservice.ConnectionType.POST, String.valueOf(jj + 1));
                links[jj + 2] = new Pair(Microservice.ConnectionType.PUT, String.valueOf(jj + 2));
                links[jj + 3] = new Pair(Microservice.ConnectionType.DELETE, String.valueOf(jj + 3));
                jj = jj + 4;
            }

            Microservice worker = null;
            try {
                worker = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        links,
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.Aggregator.toString(), Microservice.Role.Worker.toString()),
                        new AggregatorStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            aggregator.setConnections(new Pair<>(Microservice.ConnectionType.GET, worker.getURI()));
            aggregator.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker.getURI()));
            aggregator.setConnections(new Pair<>(Microservice.ConnectionType.PUT, worker.getURI()));
            aggregator.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, worker.getURI()));
            aggregator.setUsageMemory();

//            }
            matrices.add(worker);
        }
        aggregator.setUsageCPU();
        return matrices;
    }

    //**************************************************************************************************************************************
    @Override
    public Builder[] fileFiller(Microservice microservice,
                                String role,
                                String microserviceName,
                                String[] connections,

                                Pair<Microservice.ConnectionType, String>[] connectionTypes) {

        String[] uniqueArray = Arrays.stream(connections)
                .distinct()
                .toArray(String[]::new);

        Set<Pair<Microservice.ConnectionType, String>> uniqueSet = new HashSet<>();
        for (Pair<Microservice.ConnectionType, String> entry : connectionTypes) {
            uniqueSet.add(entry);
        }
        if (!uniqueSet.isEmpty()) {
            for (Pair<Microservice.ConnectionType, String> up : uniqueSet) {
                initialConnected(uniqueArray, up.getKey());
            }
        } else {
            initialConnected(uniqueArray, null);
        }
        int n = 1;
        Builder[] builder = new Builder[n];
        switch (role) {
            case "Aggregator":
                builder[0] = aggregatorClass(microserviceName);
                break;
            case "Worker":
                builder[0] = workerClass(microserviceName, connections);
                setMethods(builder[0], connectionTypes);
                break;
        }
        return builder;
    }

    private Builder aggregatorClass(String microserviceName) {
        return new Builder("microservice." + "aggregator" + ".demo.API")
                .setContent(
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
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.RequestMapping, "api/v1/" + microserviceName),
                                        new Pair<>(Constants.Annotations.RestController, "")
                                }, new String[]{"import org.springframework.web.bind.annotation.PostMapping;",
                                "import org.springframework.web.bind.annotation.RestController;"}
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
                                        new Pair(Constants.VariableType.AddNewType("ArrayList<String>"), "connections"),
                                        "",
                                        null,
                                        false,
                                        false)
                        ).setContent(
                                new BodyGenerator(
                                        new Pair[]{new Pair<>(Constants.Annotations.Autowired, "")},
                                        new ArrayList<Pair<String, String>>() {{
                                            add(new Pair<>("RestTemplate restTemplate", "RestTemplate"));
                                        }},
                                        list

                                )
                        )
                );
    }

    private Builder workerClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "aggregator" + ".demo.API")
                .setContent(
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
                                }, new String[]{"import org.springframework.web.bind.annotation.PostMapping;",
                                "import org.springframework.web.bind.annotation.RestController;"}
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

    private void setMethods(Builder builder, Pair<Microservice.ConnectionType, String>[] connectionTypes) {

        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object[]"), "getProperties"),
                null,
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, ("/getting-data"))
                },
                Constants.Keywords.NULL.toString(),
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object[]"), "getProperties"),
                null,
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, ("/inserting-data"))
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object[]"), "getProperties"),
                null,
                new Pair[]{
                        new Pair(Constants.Annotations.PutMapping, ("/updating-info"))
                },
                Constants.Keywords.NULL.toString(),
                Putlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object[]"), "getProperties"),
                null,
                new Pair[]{
                        new Pair(Constants.Annotations.DeleteMapping, ("/deleting-info"))
                },
                Constants.Keywords.NULL.toString(),
                Deletelist
        );

    }
}
