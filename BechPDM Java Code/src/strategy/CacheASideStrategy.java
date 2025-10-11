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

public class CacheAsideStrategy extends Strategy {
    public static int clientCounter = 0;
    public static int cacheCounter = 0;
    public static int workerCounter = 0;
    String clientRole = "Client";
    String cacheRole = "Cache";
    String workerRole = "Worker";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    public int i = 1;
    public int j = 1;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();

    ArrayList<Pair<Pair<String, String>, String>> varMethod = new ArrayList<>();


    public void initialVarMethod(String name) {
        varMethod.add(new Pair<>(new Pair<>("   SpringApplication.run(" + name + ".class, args);", " "), ""));
    }

    public void initialConnected(String[] connection, Microservice.ConnectionType connectionType) {

        String strcon;

        for (String str : connection) {

            if (connectionType != null) {
                strcon = str + "/" + connectionType.toString().toLowerCase() + ","
                        + "HTTP." + connectionType + "," + "entity" + "," + "String.class)";

                if (connectionType.equals(Microservice.ConnectionType.GET)) {
                    Getlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, " = restTemplate.exchange("), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.POST)) {
                    Postlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "= restTemplate.exchange("), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.PUT)) {
                    Putlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "= restTemplate.exchange("), strcon));
                } else if (connectionType.equals(Microservice.ConnectionType.DELETE)) {
                    Deletelist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "= restTemplate.exchange("), strcon));
                }

                j = j + 1;
            } else {
                strcon = str+")";
                list.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + i, "= restTemplate.exchange("), strcon));
                i = i + 1;
            }//            Microservice.ConnectionType type = pair.getKey();
        }
    }

    //#######################################################################################
    @Override
    public ArrayList<Microservice> matrixFiller() {

        ArrayList<Microservice> matrices = new ArrayList<>();
        String microserviceName;
        microserviceName = cacheRole + cacheCounter++;
        Microservice cache = null;
        try {
            String URI = URIGenerator(microserviceName);
            cache = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URI,
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/add"),
                            new Pair<>(Microservice.ConnectionType.GET, "/all"),
                            new Pair<>(Microservice.ConnectionType.PUT, "/update"),
                            new Pair<>(Microservice.ConnectionType.DELETE, "/delete/{sid}")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.CacheASide.toString(), Microservice.Role.Cache.toString()),
                    new CacheAsideStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(cache);

  //#############################################################
        int clientNumber = random.nextInt(5) + 1;


        ArrayList<Microservice> localClients = new ArrayList<>();
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
                        new Pair<>(Microservice.Pattern.CacheASide.toString(), Microservice.Role.Client.toString()),
                        new CacheAsideStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            localClients.add(client);
            matrices.add(client);

            client.setConnections(new Pair<>(Microservice.ConnectionType.POST, cache.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, cache.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.PUT, cache.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, cache.getURI()));
            client.setUsageMemory();
        }

        return matrices;
    }

    //##################################################################################
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

        int n;
        Builder[] builders = new Builder[0];

        switch (role) {
            case "Cache":
                n = 6; // تعداد Builderها
                builders = new Builder[n];

                builders[0] = cacheClass(microservice, microserviceName, connections, 0);
                // creating the controller class
                setMethods(builders[0], 0, "CacheAsideController");

                builders[1] = cacheClass(microservice, microserviceName, connections, 1);
                // creating the CacheAsideApplication class (Main class)
                setMethods(builders[1], 1, "CacheAsideApplication");

                builders[2] = cacheClass(microservice, microserviceName, connections, 2);
                // creating the repository
                setMethods(builders[2], 2, "UserRepository");

                builders[3] = cacheClass(microservice, microserviceName, connections, 3);
                // creating an Entity interface for example UserService
                setMethods(builders[3], 3, "UserService");

                builders[4] = cacheClass(microservice, microserviceName, connections, 4);
                // creating the UserServiceImpl as implementation of UserService
                setMethods(builders[4], 4, "UserServiceImpl");

                builders[5] = cacheClass(microservice, microserviceName, connections, 5);
                // creating the User class implementing Serializable


                break;
            case "Client":
                n = 1; // تعداد Builderها
                builders = new Builder[n];
                builders[0] = clientClass();
                setMethods(builders[0], 0,"ClientApplication");
                break;

        }
        return builders;
    }

    private void setMethods(Builder builder, int id, String name) {

        if (id ==0){

            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Object[]"), "findAll"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType("",""), "")
                    },
                    new Pair[]{ new Pair(Constants.Annotations.GetMapping, "/all")},
                    Constants.Keywords.AddNewType("service.findAll()"),
                    Getlist
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("User"), "findBySid"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.AddNewType("@PathVariable(sid)" ,"Integer"), "sid")
                    },
                    new Pair[]{
                            new Pair(Constants.Annotations.GetMapping,  "/findBySid/{sid}")
                    },
                    Constants.Keywords.AddNewType("service.findBySid(sid).orElse(null)"),
                    Getlist
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Integer") , "create"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.AddNewType("@RequestBody", "User"), "user")
                    },
                    new Pair[]{
                            new Pair(Constants.Annotations.PostMapping, "/add")
                    },
                    Constants.Keywords.AddNewType("service.create(user);"),
                    Postlist
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Integer") , "update"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.AddNewType("@RequestBody", "User"), "user")
                    },
                    new Pair[]{
                            new Pair<>(Constants.Annotations.PutMapping, "/update/{sid}")},
                    Constants.Keywords.AddNewType("service.update(user);"),
                    Putlist
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Integer"), "disable"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.AddNewType("PathVariable(sid)","Integer"), "sid")
                    },
                    new Pair[]{
                            new Pair(Constants.Annotations.DeleteMapping,  "/delete/{sid}")
                    },
                    Constants.Keywords.AddNewType("service.disable(sid);"),
                    Deletelist
            );

        }
        else if (id ==1) {
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
                    varMethod);
        }

    }

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    private Builder cacheClass(Microservice microservice, String microserviceName, String[] connections, int id) {
        if (id == 0) {
            return new Builder("microservice." + "cacheAside" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    microserviceName+"Controller",
                                    null,
                                    "",
                                    new ConstructorGenerator[]{
                                            new ConstructorGenerator(
                                                    Constants.AccessLevel.PUBLIC,
                                                    microserviceName+"Controller",
                                                    null,
                                                    null,
                                                    null,
                                                    new ArrayList<Pair<String, String>>() {{
                                                        add(new Pair<>("connections", "ArrayList<String>"));
                                                    }})
                                    },
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.RequestMapping, "api/v1/user" + microserviceName),
                                            new Pair<>(Constants.Annotations.RestController, "")
                                    },
                                    new String[]{"import java.util.List;",
                                            "import org.springframework.beans.factory.annotation.Autowired;",
                                            "import org.springframework.web.bind.annotation.PostMapping;",
                                            "import org.springframework.web.bind.annotation.RestController;",
                                            "import org.springframework.http.HttpStatus;",
                                            "import org.springframework.http.ResponseEntity;",
                                            "import org.springframework.web.bind.annotation.*;",
                                            "import microservice.cacheAside.demo.API.User;",
                                            "import microservice.cacheAside.demo.API.UserService;"
                                    }
                            ).setContent(
                                    new VariableGenerator(
                                            Constants.AccessLevel.PRIVATE,
                                            false,
                                            false,
                                            new Pair<>("UserService", " id"),
                                            "",
                                            new Pair[] {
                                                    new Pair<>(Constants.Annotations.Autowired, "")},
                                            false,
                                            false)
                            ).setContent(
                                    new VariableGenerator(
                                            Constants.AccessLevel.PRIVATE,
                                            false,
                                            false,
                                            new Pair<>(Constants.VariableType.AddNewType("ArrayList<String>"), " connections"),
                                            "",
                                            null,
                                            false,
                                            false)
                            ).setContent(new POMGenerator(
                                    microserviceName, Main.getPath2(),"cache-aside")

                            ).setContent(new ConfigFileGenerator(
                                    microserviceName,
                                    Main.getPath2(),
                                    microservice.getPort(),
                                    "cache-aside"))
        );} else if (id == 1) {
            return new Builder("microservice." + "cacheAside" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "cacheAsideApplication",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication,""),
                                            new Pair<>(Constants.Annotations.EnableCaching,"")},

                                    new String[]{"import org.springframework.boot.SpringApplication;",
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;"}
                            ));
        }
        else if (id ==2) {

            return new Builder("microservice." + "cacheAside" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.INTERFACE,
                                    "UserRepo",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.Repository,"")},

                                    new String[]{"org.springframework.stereotype.Repository;",
                                            "import microservice.cacheAside.demo.API.User;"}
                            )
                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.CUSTOM,
                                    false,
                                    false,
                                    new Pair<>("List<User>", " findAll()"),
                                    "",
                                    new Pair[] {},
                                    false,
                                    false)
                    )
                    .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.CUSTOM,
                            false,
                            false,
                            new Pair<>("User", " findBySid(Integer sid)"),
                            "",
                            new Pair[] {},
                            false,
                            false)
                    )

                    .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.CUSTOM,
                            false,
                            false,
                            new Pair<>("Integer", " create(User user)"),
                            "",
                            new Pair[] {},
                            false,
                            false)
                    )

                    .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.CUSTOM,
                            false,
                            false,
                            new Pair<>("Integer", " update(User user)"),
                            "",
                            new Pair[] {},
                            false,
                            false)
                    )

                    .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.CUSTOM,
                            false,
                            false,
                            new Pair<>("Integer", " disable(Integer sid)"),
                            "",
                            new Pair[] {},
                            false,
                            false)
            ));
        }
        else if (id ==3) {

            return new Builder("microservice." + "cacheAside" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.INTERFACE,
                                    "UserService",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{},

                                    new String[]{"import java.util.List;",
                                            "java.util.Optional;",
                                    "import microservice.cacheAside.demo.API;"}
                            )
                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.CUSTOM,
                                    false,
                                    false,
                                    new Pair<>("List<User>", " findAll()"),
                                    "",
                                    new Pair[] {},
                                    false,
                                    false)
                    )
                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.CUSTOM,
                                    false,
                                    false,
                                    new Pair<>("Optional<User>", " findBySid(Integer sid)"),
                                    "",
                                    new Pair[] {},
                                    false,
                                    false)
                    )

                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.CUSTOM,
                                    false,
                                    false,
                                    new Pair<>("Integer", " create(User user)"),
                                    "",
                                    new Pair[] {},
                                    false,
                                    false)
                    )

                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.CUSTOM,
                                    false,
                                    false,
                                    new Pair<>("Integer", " update(User user)"),
                                    "",
                                    new Pair[] {},
                                    false,
                                    false)
                    )

                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.CUSTOM,
                                    false,
                                    false,
                                    new Pair<>("Integer", " disable(Integer sid)"),
                                    "",
                                    new Pair[] {},
                                    false,
                                    false)
                    ));
        }
        else if (id ==4) {

            return new Builder("microservice." + "cacheAside" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "UserServiceImpl",
                                    new String[] {"UserService" },
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<> (Constants.Annotations.Service,""),
                                            new Pair<> (Constants.Annotations.Slf4j,"")
                                    },

                                    new String[]{"import org.springframework.beans.factory.annotation.Autowired;\n" +
                                            "import org.springframework.data.redis.core.RedisTemplate;\n" +
                                            "import org.springframework.data.redis.core.ValueOperations;",
                                    "import org.springframework.stereotype.Service;",
                                    "import microservice.cacheAside.demo.API.UserRepo",
                                    "import microservice.cacheAside.demo.API.UserService"}
                            )
                    .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.PRIVATE,
                            false,
                            false,
                            new Pair<>("UserRepo", " repo"),
                            "",
                            new Pair[] {
                                    new Pair<>(Constants.Annotations.Autowired,"")
                            },
                            false,
                            false))
                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.PRIVATE,
                                    false,
                                    false,
                                    new Pair<>("RedisTemplate ", " redisTemplate"),
                                    "",
                                    new Pair[] {
                                            new Pair<>(Constants.Annotations.Autowired,"")
                                    },
                                    false,
                                    false)
                    )
                    .setContent(
                            new BodyGenerator(
                                    new Pair[]{},
                                    new ArrayList<Pair<String, String>>() {{
                                        add(new Pair<>("operations", "redisTemplate.opsForValue"));
                                    }},
                                    list

                            )
                    ));
        }
        else if (id ==5){
            return new Builder("microservice." + "cacheAside" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "User",
                                    new String[]{"Serializable"},
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.Data,"")
                                    },

                                    new String[]{"import java.io.Serializable;",
                                                 "import lombok.AccessLevel;\n" +
                                                    "import lombok.Data;\n" +
                                                    "import lombok.Getter;\n" +
                                                    "import lombok.Setter;"}
                            )
                                    .setContent(
                                            new VariableGenerator(
                                                    Constants.AccessLevel.PRIVATE,
                                                    true,
                                                    true,
                                                    new Pair<>("long", " serialVersionUID = -1L"),
                                                    "",
                                                    new Pair[] {
                                                            new Pair<>(Constants.Annotations.Getter,""),
                                                            new Pair<>(Constants.Annotations.Setter,"")
                                                    },
                                                    false,
                                                    false)
                                    )
                                    .setContent(
                                            new VariableGenerator(
                                                    Constants.AccessLevel.PRIVATE,
                                                    false,
                                                    false,
                                                    new Pair<>("Integer", " sid"),
                                                    "",
                                                    new Pair[] {},
                                                    false,
                                                    false)
                                    )

                                    .setContent(
                                            new VariableGenerator(
                                                    Constants.AccessLevel.PRIVATE,
                                                    false,
                                                    false,
                                                    new Pair<>("Boolean", " enabled"),
                                                    "",
                                                    new Pair[] {},
                                                    false,
                                                    false)
                                    )

                                    .setContent(
                                            new VariableGenerator(
                                                    Constants.AccessLevel.PRIVATE,
                                                    false,
                                                    false,
                                                    new Pair<>("Date ", " lastModified"),
                                                    "",
                                                    new Pair[] {},
                                                    false,
                                                    false)
                                    )

                                    .setContent(
                                            new VariableGenerator(
                                                    Constants.AccessLevel.PRIVATE,
                                                    false,
                                                    false,
                                                    new Pair<>("Integer", " lastModifiedBy"),
                                                    "",
                                                    new Pair[] {},
                                                    false,
                                                    false)
                                    )
                    .setContent(
                            new VariableGenerator(
                                    Constants.AccessLevel.PRIVATE,
                                    false,
                                    false,
                                    new Pair<>("String", " id"),
                                    "",
                                    new Pair[] {},
                                    false,
                                    false)
                    )
             .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.PRIVATE,
                            false,
                            false,
                            new Pair<>("String", " name"),
                            "",
                            new Pair[] {},
                            false,
                            false)
            )
             .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.PRIVATE,
                            false,
                            false,
                            new Pair<>("Integer", " primaryOrg"),
                            "",
                            new Pair[] {},
                            false,
                            false)
            )
             .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.PRIVATE,
                            false,
                            false,
                            new Pair<>("String", " customSettings"),
                            "",
                            new Pair[] {},
                            false,
                            false)
            )
             .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.PRIVATE,
                            false,
                            false,
                            new Pair<>("String", " email"),
                            "",
                            new Pair[] {},
                            false,
                            false)
            ));


        }

        return null;
    }

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    private Builder clientClass() {
        return new Builder("microservice." + "cacheaside" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "ClientController",
                                null,
                                "",
                                new ConstructorGenerator[]{
                                        new ConstructorGenerator(
                                                Constants.AccessLevel.PUBLIC,
                                                "ClientController",
                                                null,
                                                null,
                                                null,
                                                new ArrayList<Pair<String, String>>() {{
                                                    add(new Pair<>("connections", "ArrayList<String>"));
                                                }})
                                },
                                new Pair[]{},
                                new String[] {}
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
                        ));
    }

}
