package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.*;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.*;

public class LoadBalancerStrategy extends Strategy {
    public static int loadBalancerCounter = 0;
    public static int providerCounter = 0;
    String loadBalancer = "LoadBalancingService";
    String providerRole = "ProviderService";
    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    public int i = 1;
    public int j = 1;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();

    ArrayList<Pair<Pair<String, String>, String>> varMethod = new ArrayList<>();

    private static List<Integer> providersPorts = new ArrayList<>();



    public void initialVarMethod(String name) {
        varMethod.add(new Pair<>(new Pair<>("   SpringApplication.run(" + name + ".class, args);", " "), ""));
    }

    //###################################################################
    public ArrayList<Pair<Pair<String, String>, String>> initialListMethod(String[] connection, String[] connectionType) {
        String strcon;
        Set<String> set = new HashSet<>(Arrays.asList(connection));

        String[] uniqueConnection = set.toArray(new String[0]);

        int j = 1;
        for (String str : uniqueConnection) {
            for (String conType : connectionType) {
                strcon = "(" + str + "/" + conType.toLowerCase() + ","
                        + "HTTP." + conType + "," + "entity" + "," + "String.class)";
                list.add(new Pair<>(new Pair<>("ResponseEntity<String> response" + j + "= (", "restTemplate.exchange"), strcon));
                j++;
            }
        }
        return list;
    }

    //###########################################################
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
        int providerNumber = random.nextInt(5) + 2;
        String microserviceName = loadBalancer + loadBalancerCounter++;

        Microservice loadBalancer = null;

        try {
            loadBalancer = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.GET,"/call-provider")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.LoadBalanceing.toString(), Microservice.Role.LoadBalancingService.toString()),
                    new LoadBalancerStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        matrices.add(loadBalancer);

        int port;
        for (int i = 0; i < providerNumber; i++) {
            microserviceName = providerRole + providerCounter++;
            Microservice provider = null;
            String URI;
            URI=URIGenerator(microserviceName);
            port =getPort();
            providersPorts.add(port);
            try {
                provider = new Microservice(
                        port,
                        id++,
                        microserviceName,
                        URI,
                        new Pair[]{
                                new Pair<>(Microservice.ConnectionType.POST, ""),
                                new Pair<>(Microservice.ConnectionType.PUT, "/properties"),
                                new Pair<>(Microservice.ConnectionType.GET, "/hello")
                        },
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.LoadBalanceing.toString(), Microservice.Role.ProviderService.toString()),
                        new LoadBalancerStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            loadBalancer.setConnections(new Pair<>(Microservice.ConnectionType.POST, provider.getURI()));
            loadBalancer.setConnections(new Pair<>(Microservice.ConnectionType.PUT, provider.getURI()));
            loadBalancer.setConnections(new Pair<>(Microservice.ConnectionType.GET, provider.getURI()));
            loadBalancer.setUsageMemory();
            matrices.add(provider);
        }
        System.out.println("Filled ports: " + providersPorts);

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
            case "ProviderService":
                n = 1; // تعداد Builderها
                builders = new Builder[n];

                builders[0] = ProviderServiceClass(microservice, microserviceName, connections, 0);
                ProviderServiceMethods(builders[0], 0, "ProviderServiceApplication");

                break;

            case "LoadBalancingService":
                n = 2; // تعداد Builderها
                builders = new Builder[n];

                builders[0] = LoadBalancingServiceClass(microservice, microserviceName, connections, 0);
                LoadBalancingServiceMethods(builders[0], 0, "LoadBalancingServiceApplication");

                builders[1] = LoadBalancingServiceClass(microservice, microserviceName, connections, 1);
                LoadBalancingServiceMethods(builders[1], 1, "ServiceInstanceConfig");
                break;
        }
        return builders;
    }

    /* *********************************
     ************************************
     * *********************************/
    private Builder ProviderServiceClass(Microservice microservice, String microserviceName, String[] connections, int id) {
        if (id == 0) {
            System.out.println("microserviceNameRole: " + microservice.getRole() + "and id =" + id + "\n");
            return new Builder("microservice." + "loadBalancing" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "ProviderServiceApplication",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication, ""),
                                            new Pair<>(Constants.Annotations.RestController, "")},

                                    new String[]{"import org.springframework.boot.SpringApplication;\n" +
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                                            "import org.springframework.web.bind.annotation.*;\n"}
                            ).setContent(new POMGenerator(
                                            microserviceName, Main.getPath2(),"provider"))
                                    .setContent(new ConfigFileGenerator(
                                            microserviceName,
                                            Main.getPath2(),microservice.getPort(),"load-balancer"))
                    );
        } else {
            return null;
        }
    }

    /* *********************************
     ************************************
     * *********************************/
    private Builder LoadBalancingServiceClass(Microservice microservice, String microserviceName, String[] connections, int id) {
        if (id == 0) {
            System.out.println("microserviceNameRole: " + microservice.getRole() + "and id =" + id + "\n");
            return new Builder("microservice." + "loadBalancing" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "LoadBalancingServiceApplication",
                                    null,
                                    "",
                                    new ConstructorGenerator[]{
                                            new ConstructorGenerator(
                                                    Constants.AccessLevel.PUBLIC,
                                                    "LoadBalancingServiceApplication",
                                                    null,
                                                    new Pair[]{
                                                            new Pair<>(Constants.Annotations.setContent(""), " RestTemplate restTemplate"),
                                                    },
                                                    null,
                                                    new ArrayList<Pair<String, String>>() {{
                                                        add(new Pair<>("this.restTemplate", "restTemplate"));
                                                    }})
                                    },
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.SpringBootApplication, ""),
                                            new Pair<>(Constants.Annotations.RestController, "")},

                                    new String[]{"import org.springframework.boot.SpringApplication;\n" +
                                            "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                                            "import org.springframework.cloud.client.loadbalancer.LoadBalanced;\n" +
                                            "import org.springframework.context.annotation.Bean;\n" +
                                            "import org.springframework.web.bind.annotation.*;\n" +
                                            "import org.springframework.web.client.RestTemplate;"}
                            ).setContent(
                                    new VariableGenerator(
                                            Constants.AccessLevel.PRIVATE,
                                            false,
                                            true,
                                            new Pair<>(Constants.VariableType.AddNewType("RestTemplate"), " restTemplate"),
                                            "",
                                            null,
                                            false,
                                            false))
                                    .setContent(new POMGenerator(
                                            microserviceName, Main.getPath2(),"load-balancer"))
                                    .setContent(new ConfigFileGenerator(
                                            microserviceName,
                                            Main.getPath2(),microservice.getPort(),"load-balancer"))
                    );
        }
        if (id == 1) {
            System.out.println("microserviceNameRole: " + microservice.getRole() + "and id =" + id + "\n");
            return new Builder("microservice." + "loadBalancing" + ".demo.API")
                    .setContent(
                            new ClassGenerator(
                                    Constants.AccessLevel.PUBLIC,
                                    Constants.ElementType.CLASS,
                                    "ServiceInstanceConfig",
                                    null,
                                    "",
                                    null,
                                    new Pair[]{
                                            new Pair<>(Constants.Annotations.Configuration, "")},

                                    new String[]{"import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;\n" +
                                            "import org.springframework.context.annotation.Bean;\n" +
                                            "import org.springframework.context.annotation.Configuration;\n" +
                                            "import reactor.core.publisher.Flux;\n" +
                                            "import org.springframework.cloud.client.ServiceInstance;\n" +
                                            "import org.springframework.cloud.client.DefaultServiceInstance;\n" +
                                            "import java.util.Arrays;"}
                            )
                    );
        } else {
            return null;
        }
    }


    /* *********************************
     ************************************
     * *********************************/
    private void ProviderServiceMethods(Builder builder, int id, String name) {

        if (id == 0) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    true,
                    false,
                    new Pair(Constants.ReturnType.VOID, "main"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "String[]", "args"), "")
                    },
                    new Pair[]{},
                    Constants.Keywords.NULL.toString(),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("SpringApplication.run(ProviderServiceApplication.class, args);", " "), ""));
                    }}
            );

            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("String"), "hello"),
                    null,
                    new Pair[]{new Pair(Constants.Annotations.GetMapping, "/hello")},
                    Constants.Keywords.AddNewType(" \"Hello from Provider on port \" + System.getProperty(\"server.port\");"),
                    null
            );
        }
    }

    /* *********************************
     ************************************
     * *********************************/
    private void LoadBalancingServiceMethods(Builder builder, int id, String name) {

        if (id == 0) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    true,
                    false,
                    new Pair(Constants.ReturnType.VOID, "main"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            " ", "String[]"), "args")
                    },
                    new Pair[]{},
                    Constants.Keywords.NULL.toString(),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("SpringApplication.run(ProviderServiceApplication.class, args);", " "), ""));
                    }}
            );

            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("RestTemplate"), " restTemplate"),
                    null,
                    new Pair[]{new Pair<>(Constants.Annotations.Bean, ""),
                            new Pair<>(Constants.Annotations.LoadBalanced, ""),
                    },
                    Constants.Keywords.AddNewType("new RestTemplate();"),
                    null
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("String"), " callProvider"),
                    null,
                    new Pair[]{new Pair<>(Constants.Annotations.GetMapping, "/call-provider")},
                    Constants.Keywords.AddNewType(" restTemplate.getForObject(\"http://provider-service/hello\", String.class);"),
                    null
            );
        } else if (id == 1) {
            StringBuilder instancesBuilder = new StringBuilder();
            int index = 1;
            System.out.println("Using ports: " + providersPorts);


            for (Integer port : providersPorts) {
                instancesBuilder.append("                        new DefaultServiceInstance(\"provider")
                        .append(index++)
                        .append("\", \"provider-service\", \"localhost\", ")
                        .append(port)
                        .append(", false),\n");
            }

            if (instancesBuilder.length() > 0) {
                instancesBuilder.setLength(instancesBuilder.length() - 2);
                instancesBuilder.append("\n");
            }
            String code = "return new ServiceInstanceListSupplier() {\n" +
                    "    @Override\n" +
                    "    public String getServiceId() {\n" +
                    "        return \"provider-service\";\n" +
                    "    }\n\n" +
                    "    @Override\n" +
                    "    public Flux<List<ServiceInstance>> get() {\n" +
                    "        return Flux.just(Arrays.asList(\n" +
                    instancesBuilder.toString() +
                    "        ));\n" +
                    "    }\n" +
                    "};";
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    true,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("ServiceInstanceListSupplier"), "serviceInstanceListSupplier"),
                    null,
                    new Pair[]{
                            new Pair<>(Constants.Annotations.Bean, "")
                    },
                    Constants.Keywords.NULL.toString(),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>(code, " "), ""));
                    }}
            );
        }
    }
}


