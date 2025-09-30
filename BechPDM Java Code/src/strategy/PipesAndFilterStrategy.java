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

public class PipesAndFilterStrategy extends Strategy {
    public static int filterCounter = 0;
    public static int pointCounter = 0;
    String filterRole = "Filter";
    String pointRole = "accessPoints";
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
        int filterNumber = random.nextInt(5) + 2;
        for (int i = 0; i < filterNumber; i++) {
            microserviceName = filterRole + filterCounter++;
            Microservice filter = null;
            try {
                filter = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, "/send_address"),
                                new Pair<>(Microservice.ConnectionType.POST, "/send_data"),
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.PipesAndFilters.toString(), Microservice.Role.Filter.toString()),
                        new PipesAndFilterStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(filter);
            if(i>0) {
                matrices.get(i - 1).setConnections(new Pair<>(Microservice.ConnectionType.POST, matrices.get(i).getURI()));
                matrices.get(i - 1).setConnections(new Pair<>(Microservice.ConnectionType.POST, matrices.get(i).getURI()));
                matrices.get(i-1).setUsageMemory();
            }
        }

        int pointNumber = 2;
        Microservice point = null;
        for (int i = 0; i < pointNumber; i++) {
            microserviceName = pointRole + pointCounter++;

            try {
                point = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, "send_result")
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.PipesAndFilters.toString(), Microservice.Role.Point.toString()),
                        new PipesAndFilterStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(point);
            if(i ==0 ){
                point.setConnections(new Pair<>(Microservice.ConnectionType.POST, matrices.get(0).getURI()));
                point.setConnections(new Pair<>(Microservice.ConnectionType.POST, matrices.get(0).getURI()));
                point.setUsageMemory();
            }
            if(i ==1 ){
                matrices.get(matrices.size()-2).setConnections(new Pair<>(Microservice.ConnectionType.POST, point.getURI()));
                matrices.get(matrices.size()-2).setUsageMemory();
            }

        }
        point.setUsageCPU();
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
            case "Filter":
                builder = filterClass(microserviceName, connections);
                setMethodsFilter(builder);
                break;
            case "Point":
                builder = pointClass(microserviceName, connections);
                setMethodsPoint(builder);
                break;
        }
        return builder;
    }

    private Builder filterClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "pipesandfilter" + ".demo.API")
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
                                        new Pair<>(Constants.VariableType.AddNewType("String"), "connection"),
                                        "",
                                        null,
                                        false,
                                        false)
                        )
                );
    }

    private void setMethodsFilter(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "sendData"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("INT"), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/send_data")
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.BOOLEAN, "sendAddress"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("INT"), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/send_address")
                },
                Constants.Keywords.FALSE.toString(),
                Postlist
        );
    }

    private Builder pointClass(String microserviceName, String[] connections) {
        return new Builder("microservice." + "pipesandfilter" + ".demo.API")
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

    private void setMethodsPoint(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "sendResult"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("INT"), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/send_result")
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
    }
}
