package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.ClassGenerator;
import content_generation.generator.ConstructorGenerator;
import content_generation.generator.VariableGenerator;
import data_structure.Microservice;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomStrategy extends Strategy {
    private static int postCounter = 0;
    private static int getCounter = 0;
    private static int deleteCounter = 0;
    private static int putCounter = 0;
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
        return null;
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
         builder[0] = baseClass(microserviceName);
        for (Pair connectionType : connectionTypes) {
            switch ((Microservice.ConnectionType) connectionType.getKey()) {
                case POST:
                    postMethod(builder[0]);
                    break;
                case GET:
                    getMethod(builder[0]);
                    break;
                case PUT:
                    putMethod(builder[0]);
                    break;
                case DELETE:
                    deleteMethod(builder[0]);
                    break;
                default:
                    System.err.println("Not Supported");
            }
        }
        return builder;
    }


    private Builder baseClass(String microserviceName) {
        return new Builder("microservice." + Microservice.Pattern.CustomPattern + ".demo.API")
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
                                                }})
                                },
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.RequestMapping, "api/v1/" + microserviceName),
                                        new Pair<>(Constants.Annotations.RestController, "")
                                },new String[]{"import org.springframework.web.bind.annotation.PostMapping;"}
                        ).setContent(
                                new VariableGenerator(Constants.AccessLevel.PRIVATE,
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

    private void postMethod(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair<>(Constants.ReturnType.BOOLEAN, "PostMethod" + postCounter++),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.Object, "object")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/post" + postCounter)
                },
                Constants.Keywords.FALSE.toString(),
                Postlist
        );
    }

    private void getMethod(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.AddNewType("Object"), "GetMethod" + getCounter++),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.AddNewType("int",""), "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "/get" + getCounter)
                },
                Constants.Keywords.NULL.toString(),
                Getlist
        );
    }


    private void deleteMethod(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair<>(Constants.ReturnType.BOOLEAN, "DeleteMethod" + deleteCounter++),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.Object, "object")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.DeleteMapping, "/delete" + deleteCounter)
                },
                Constants.Keywords.FALSE.toString(),
                Deletelist
        );
    }

    private void putMethod(Builder builder) {
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair<>(Constants.ReturnType.BOOLEAN, "PutMethod" + putCounter++),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.Object, "object")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PutMapping, "/put" + putCounter)
                },
                Constants.Keywords.FALSE.toString(),
                Putlist
        );
    }
}
