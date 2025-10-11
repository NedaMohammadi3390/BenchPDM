package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.*;
import data_structure.Counter;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ApiGatewayStrategy extends Strategy {

    public static int apiGatewayCounter = 0;
    public static int workerCounter = 0;
    String clientRole = "Client";
    String gatewayRole = "ApiGateway";
    String workerRole = "Worker";

    public int i = 1;
    public int j = 1;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> varMethod = new ArrayList<>();


    public void initialVarMethod(String name){
        varMethod.add(new Pair<>(new Pair<>("   SpringApplication.run("+name+".class, args);", " "), ""));
    }
//###################################################################
    public ArrayList<Pair<Pair<String, String>, String>> initialListMethod(String[] connection, String[] connectionType){
        String strcon;
        Set<String> set = new HashSet<>(Arrays.asList(connection));

        String[] uniqueConnection = set.toArray(new String[0]);

        int j=1;
        for (String str : uniqueConnection) {
            for(String conType : connectionType){
                strcon = "(" + str + "/" +conType.toLowerCase() + ","
                        + "HTTP." + conType + "," + "entity" + "," + "String.class)";
                list.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j + "= (", "restTemplate.exchange"), strcon));
                j++;
        }}
        return list;
    }
//###########################################################
    public void initialConnected(String[] connection, Microservice.ConnectionType connectionType) {

        String strcon;
        for (String str : connection) {

            if (connectionType != null) {
                strcon = "(" + str + "/" + connectionType.toString().toLowerCase() + ","
                        + "HTTP." + connectionType + "," + "entity" + "," + "String.class)";

                if (connectionType.equals(Microservice.ConnectionType.GET)) {
                    Getlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j + "= (", "restTemplate.exchange"), strcon));

                } else if (connectionType.equals(Microservice.ConnectionType.POST)) {
                    Postlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j + "= (", "restTemplate.exchange"), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.PUT)) {
                    Putlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j + "= (", "restTemplate.exchange"), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.DELETE)) {
                    Deletelist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j + "= (", "restTemplate.exchange"), strcon));
                }

                j = j + 1;
            } else {
                strcon = str+")";
                list.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + i, "= restTemplate.exchange("), strcon));
                i = i + 1;
            }
        }
    }

    @Override
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();
        String microserviceName;
        microserviceName = gatewayRole + apiGatewayCounter++;
        Microservice apiGateway = null;
        try {
            String URI = URIGenerator(microserviceName);
            apiGateway = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URI,
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/insert_info"),
                            new Pair<>(Microservice.ConnectionType.PUT, "/update_info"),
                            new Pair<>(Microservice.ConnectionType.GET, "/get_info"),
                            new Pair<>(Microservice.ConnectionType.DELETE, "/delete_info")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.ApiGateway.toString(), Microservice.Role.ApiGateway.toString()),
                    new ApiGatewayStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(apiGateway);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int clientNumber = random.nextInt(2) + 3;
        for (int i = 0; i < clientNumber; i++) {

            microserviceName = clientRole + Counter.getClientNext();
            Microservice client = null;
            try {
                client = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{},
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.ApiGateway.toString(), Microservice.Role.Client.toString()),
                        new ApiGatewayStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(client);
            client.setConnections(new Pair<>(Microservice.ConnectionType.POST, apiGateway.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, apiGateway.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.PUT, apiGateway.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, apiGateway.getURI()));
            client.setUsageMemory();
        }
//////////////////////////////////////////////////////////
        int workerNumber = random.nextInt(3) + 1;
        for (int ii = 0; ii < workerNumber; ii++) {
            microserviceName = workerRole + Counter.getWorkerNext();
            Microservice worker = null;
            try {
                worker = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, "post_info"),
                                new Pair<>(Microservice.ConnectionType.GET, "get_info"),
                                new Pair<>(Microservice.ConnectionType.PUT, "put_info"),
                                new Pair<>(Microservice.ConnectionType.DELETE, "delete_info")
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.ApiGateway.toString(), Microservice.Role.Worker.toString()),
                        new ApiGatewayStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(worker);
            apiGateway.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker.getURI()));
            apiGateway.setConnections(new Pair<>(Microservice.ConnectionType.GET, worker.getURI()));
            apiGateway.setConnections(new Pair<>(Microservice.ConnectionType.PUT, worker.getURI()));
            apiGateway.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, worker.getURI()));
            apiGateway.setUsageMemory();

        }
        return matrices;
    }

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    @Override
    public Builder[] fileFiller(
            Microservice microservice,
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

        int n = 2;
        Builder[] builders = new Builder[n];


        Builder builder = new Builder();
        Builder builder2 = new Builder();
        switch (role) {
            case "Client":
                builder = clientClass(microservice,microserviceName,connections,1);
                builder2 = clientClass(microservice,microserviceName,connections,2);
                setMethods(builder2, 2,"ClientApplication");
                break;
            case "ApiGateway":
                builder = apiGatewayClass(microservice,microserviceName, connections, 1);
                setMethods(builder, 1,"ZullApplication");
                builder2 = apiGatewayClass(microservice,microserviceName, connections, 2);
                setMethods(builder2, 2,"ZullApplication");

                break;
            case "Worker":
                builder = workerClass(microservice,microserviceName, connections,1);
                setMethodsWorker(builder,1,"WorkerApplication");
                builder2 = workerClass(microservice,microserviceName, connections,2);
                setMethodsWorker(builder2,2,"WorkerApplication");
                break;
        }
        builders[0] = builder;
        builders[1] = builder2;
        return builders;
    }



    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    private Builder apiGatewayClass(Microservice microservice,
                                    String microserviceName, String[] connections, int id) {
        if (id == 1) {
            return new Builder("microservice." + "apigateway" + ".demo.API")
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
                                    },
                                    new String[]{"import org.springframework.web.bind.annotation.PostMapping;",
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
                            ).setContent(new POMGenerator(
                                    microserviceName, Main.getPath2(),"api-gateway")
                            ).setContent(new ConfigFileGenerator(
                                    microserviceName,
                                    Main.getPath2(),microservice.getPort(),"api-gateway"))

                    );
        } else if (id == 2) {
            return new Builder("microservice." + "apigateway" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "ZuulApplication",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication,""),
                                            new Pair<>(Constants.Annotations.EnableZuulProxy, "")},

                                    new String[]{"import org.springframework.boot.SpringApplication;",
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;",
                                            "import org.springframework.cloud.netflix.zuul.EnableZuulProxy;"}
                    ));
        }
        else {return null;}

    }
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    private Builder clientClass(Microservice microservice,
                                String microserviceName, String[] connections, int id) {
        if (id ==1){
            return new Builder("microservice." + "apigateway" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "ClientAPI",
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
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.Component,"")},
                                    new String[]{"import org.springframework.beans.factory.annotation.Autowired;",
                                            "import org.springframework.stereotype.Component;",
                                            "org.springframework.web.client.RestTemplate;"}
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
                                            new Pair[]{new Pair<>(Constants.Annotations.Autowired, "")},
                                            new ArrayList<Pair<String, String>>() {{
                                                add(new Pair<>("RestTemplate restTemplate", "RestTemplate"));
                                            }},
                                            initialListMethod(connections, new String[]{"Post", "Put", "Get", "Delete"})

                                    )
                            ).setContent(new POMGenerator(
                                            microserviceName, Main.getPath2(),"api-gateway"))
                             .setContent(new ConfigFileGenerator(
                                    microserviceName,
                                    Main.getPath2(),microservice.getPort(),"api-gateway"))
                    );}
        else if (id == 2) {
            return new Builder("microservice." + "apigateway" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "ClientApplication",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication,"")},

                                    new String[]{"import org.springframework.boot.SpringApplication;",
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;",
                                            "import org.springframework.web.client.RestTemplate;"}
                            ));
        }
        else {return null;}
    }

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */


    private Builder workerClass(Microservice microservice,
                                String microserviceName, String[] connections, int id) {
        if (id==1){
        return new Builder("microservice." + "apigateway" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "WorkerController",
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
                                },new String[]{"import org.springframework.beans.factory.annotation.Autowired;",
                                "import org.springframework.web.bind.annotation.PostMapping;",
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
                        ).setContent(new POMGenerator(
                                microserviceName, Main.getPath2(),"api-gateway"))
                         .setContent(new ConfigFileGenerator(
                                        microserviceName,
                                        Main.getPath2(),microservice.getPort(),"api-gateway"))
                );}
    else if (id == 2) {
        return new Builder("microservice." + "apigateway" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "WorkerApplication",
                                null,
                                "",
                                null,
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.SpringBootApplication,""),
                                        new Pair<>(Constants.Annotations.EnableDiscoveryClient, "")},

                                new String[]{"import org.springframework.boot.SpringApplication;",
                                        "import org.springframework.boot.autoconfigure.SpringBootApplication;",
                                        "import org.springframework.cloud.client.discovery.EnableDiscoveryClient;"}));
    }
        else {return null;}
    }


    private void setMethods(Builder builder, int id, String name) {
        if(id == 1){
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "creating-request"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/creating_request")
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "receiving-data"),
                null,
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "/receiving-info")
                },
                Constants.Keywords.FALSE.toString(),
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "updating-data"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PutMapping, "/updating-info")
                },
                Constants.Keywords.NULL.toString(),
                Putlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "deleting-data"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "id")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.DeleteMapping, "/deleting-info")
                },
                Constants.Keywords.NULL.toString(),
                Deletelist
        );
        }
        else if(id == 2) {
            initialVarMethod(name);
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    true,
                    false,
                    new Pair(Constants.ReturnType.VOID, "main"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody2.String, "[] args")
                    },
                    null,
                    "",
                    varMethod

            );
        }
    }


    private void setMethodsWorker(Builder builder, int id,String name) {
        if (id==1){
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "receiving-data"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.GetMapping, "/receive_info")
                },
                Constants.Keywords.NULL.toString(),
                Getlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "inserting-data"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PostMapping, "/inserting-info")
                },
                Constants.Keywords.NULL.toString(),
                Postlist
        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "deleting-data"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.DeleteMapping, "/deleting-info")
                },
                Constants.Keywords.NULL.toString(),
                Deletelist

        );
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                false,
                false,
                new Pair(Constants.ReturnType.INT, "updating-data"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                },
                new Pair[]{
                        new Pair(Constants.Annotations.PutMapping, "/updating-info")
                },
                Constants.Keywords.NULL.toString(),
                Putlist

        );}
        else if(id == 2) {
        initialVarMethod(name);
        setMethodContent(
                builder,
                Constants.AccessLevel.PUBLIC,
                true,
                false,
                new Pair(Constants.ReturnType.VOID, "main"),
                new Pair[]{
                        new Pair<>(Constants.VariableTypeRequestBody2.String, "[] args")
                },
                null,
                "",
                varMethod

        );
    }
    }

}
