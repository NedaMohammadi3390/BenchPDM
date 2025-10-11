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

public class ServiceDiscoveryStrategy extends Strategy {
    public static int clientCounter = 0;
    public static int clientHostId = -1;
    public static int serviceRegistryCounter = 0;
    public static int workerCounter = 0;
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;

    String clientRole = "Client";
    String serviceRegistryRole = "ServiceRegistry";
    String workerRole = "Worker";
    public int i = 1;
    public int j = 1;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();
    Microservice worker;
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
                    Getlist.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j, "= restTemplate.exchange("), strcon));
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
                list.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + i, "= restTemplate.exchange"), strcon));
                i = i + 1;
            }
        }
    }

    @Override
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();
        String microserviceName;

        // Registry
        microserviceName = serviceRegistryRole + serviceRegistryCounter++;
        Microservice serviceRegistry = null;
        try {
            serviceRegistry = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/register_service"),
                            new Pair<>(Microservice.ConnectionType.PUT, "/update_service"),
                            new Pair<>(Microservice.ConnectionType.GET, "/service_address"),
                            new Pair<>(Microservice.ConnectionType.DELETE, "/destroy_service")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.ServiceDiscovery.toString(), Microservice.Role.ServiceRegistry.toString()),
                    new ServiceDiscoveryStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(serviceRegistry);


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Worker
        int workerNumber = random.nextInt(5) + 1;
        for (int i = 0; i < workerNumber; i++) {
            microserviceName = workerRole + Counter.getWorkerNext();
            try {
                worker = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{new Pair<>(Microservice.ConnectionType.POST, "/register_service"),
                                new Pair<>(Microservice.ConnectionType.PUT, "/update_service"),
                                new Pair<>(Microservice.ConnectionType.GET, "/service_address"),
                                new Pair<>(Microservice.ConnectionType.DELETE, "/destroy_service")},
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.ServiceDiscovery.toString(), Microservice.Role.Worker.toString()),
                        new ServiceDiscoveryStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(worker);
            worker.setConnections(new Pair<>(Microservice.ConnectionType.POST, serviceRegistry.getURI()));
            worker.setConnections(new Pair<>(Microservice.ConnectionType.PUT, serviceRegistry.getURI()));
            worker.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, serviceRegistry.getURI()));
            worker.setUsageMemory();

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        // Client
        Microservice client = null;
        int clientNumber = random.nextInt(6) + 3;
        for (int i = 0; i < clientNumber; i++) {
            microserviceName = clientRole + Counter.getClientNext();

            try {
                client = new Microservice(
                        getPort(),
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        new Pair[]{},
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.ServiceDiscovery.toString(), Microservice.Role.Client.toString()),
                        new ServiceDiscoveryStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(client);
            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, serviceRegistry.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.GET, worker.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.PUT, worker.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker.getURI()));
            client.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, worker.getURI()));
            client.setUsageMemory();
        }


        client.setUsageCPU();
        return matrices;
    }

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
            case "ServiceRegistry":
                 n = 2; // تعداد Builderها
                 builders = new Builder[n];

                builders[0] = serviceRegistryClass(microservice,microserviceName, connections, 1);
                setMethodsServiceRegistry(builders[0],1,"ServiceRegistryApplication");
                builders[1] = serviceRegistryClass(microservice, microserviceName, connections, 2);
                setMethodsServiceRegistry(builders[1], 2, "serviceDiscoveryApplication");
                break;

            case "Worker":
                n = 2; // تعداد Builderها
                builders = new Builder[n];

                builders[0] = workerClass(microservice, microserviceName, connections, 1);
                setMethodsWorker(builders[0], 1, "WorkerApplication");
                builders[1] = workerClass(microservice, microserviceName, connections, 2);
                setMethodsWorker(builders[1], 2, "WorkerApplication");
                break;
            case "Client":
                n = 2; // تعداد Builderها
                builders = new Builder[n];

                builders[0] = clientClass(microservice, microserviceName, connections, 1);
                builders[1] = clientClass(microservice, microserviceName, connections, 2);
                setMethodsWorker(builders[1], 2, "ClientApplication");
                break;

        }
        return builders;
    }

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    private Builder serviceRegistryClass(Microservice microservice, String microserviceName, String[] connections, int id) {
        if (id == 1) {
            return new Builder("microservice." + "serviceDiscovery" + ".demo.API")
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
                                    microserviceName, Main.getPath2(),"service-discovery")
                            ).setContent(new ConfigFileGenerator(
                                    microserviceName,
                                    Main.getPath2(), microservice.getPort(), "service-discovery"))
                    );
        } else if (id == 2) {
            return new Builder("microservice." + "serviceDiscovery" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "serviceDiscoveryApplication",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication, ""),
                                            new Pair<>(Constants.Annotations.EnableEurekaServer, "")},

                                    new String[]{"import org.springframework.boot.SpringApplication;",
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;",
                                            "import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;"}
                            )).setContent(new POMGenerator(
                            microserviceName, Main.getPath2(),"service-discovery")
                    ).setContent(new ConfigFileGenerator(
                            microserviceName,
                            Main.getPath2(), microservice.getPort(), "service-discovery"));
        } else {
            return null;
        }
    }

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    private Builder clientClass(Microservice microservice,
                                String microserviceName, String[] connections, int id) {
        if (id == 1) {
            return new Builder("microservice." + "servicediscovery" + ".demo.API")
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
                                    new Pair[]{new Pair<>(Constants.Annotations.Component, "")},
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
                                            new Pair[]{new Pair<>(Constants.Annotations.Autowired, "")},
                                            new ArrayList<Pair<String, String>>() {{
                                                add(new Pair<>("RestTemplate restTemplate", "RestTemplate"));
                                            }},
                                            list

                                    )
                            )
                    );
        } else if (id == 2) {
            return new Builder("microservice." + "servicediscovery" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "ClientApplication",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication, "")},

                                    new String[]{"import org.springframework.boot.SpringApplication;",
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;",
                                            "import org.springframework.web.client.RestTemplate;"}
                            ));
        } else {
            return null;
        }
    }
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */

    private Builder workerClass(Microservice microservice,
                                String microserviceName, String[] connections, int id) {
        if (id == 1) {
            return new Builder("microservice." + "servicediscovery" + ".demo.API")
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
                                    }, new String[]{"import org.springframework.beans.factory.annotation.Autowired;",
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
                                            microserviceName, Main.getPath2(),"service-discovery"))
                                    .setContent(new ConfigFileGenerator(
                                            microserviceName,
                                            Main.getPath2(), microservice.getPort(), "service-discovery"))
                    );
        } else if (id == 2) {
            return new Builder("microservice." + "servicediscovery" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "WorkerApplication",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication, ""),
                                            new Pair<>(Constants.Annotations.EnableDiscoveryClient, "")},

                                    new String[]{"import org.springframework.boot.SpringApplication;",
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;",
                                            "import org.springframework.cloud.client.discovery.EnableDiscoveryClient;"}));
        } else {
            return null;
        }
    }

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */

    private void setMethodsServiceRegistry(Builder builder, int id, String name) {
        if (id == 1) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Object"), "getServiceAddress"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.String, "info")
                    },
                    new Pair[]{
                            new Pair(Constants.Annotations.GetMapping, "/service_address")
                    },
                    Constants.Keywords.NULL.toString(),
                    Getlist
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Object"), "registerService"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.String, "id")
                    },
                    new Pair[]{
                            new Pair(Constants.Annotations.PostMapping, "/register_service")
                    },
                    Constants.Keywords.NULL.toString(),
                    Postlist
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Object"), "destroyService"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.String, "id")
                    },
                    new Pair[]{
                            new Pair(Constants.Annotations.DeleteMapping, "/destroy_service")
                    },
                    Constants.Keywords.NULL.toString(),
                    Deletelist
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("Object"), "updateService"),
                    new Pair[]{
                            new Pair<>(Constants.VariableTypeRequestBody.String, "id")
                    },
                    new Pair[]{
                            new Pair(Constants.Annotations.PutMapping, "/update_service")
                    },
                    Constants.Keywords.NULL.toString(),
                    Putlist
            );
        } else if (id == 2) {
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
            System.out.println(varMethod.size());

        }
    }

    private void setMethodsWorker(Builder builder, int id, String name) {
        if (id == 1) {
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

            );
        } else if (id == 2) {
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
