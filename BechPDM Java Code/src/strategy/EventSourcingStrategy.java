package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.*;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EventSourcingStrategy extends Strategy {
    public static int clientCounter = 0;
    public static int orderRoleCounter = 0;
    public static int shippingRoleCounter = 0;

    String orderRole = "OrderService";
    String shippingRole = "ShippingService";
   
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

    //###################################################################
    public ArrayList<Pair<Pair<String, String>, String>> initialListMethod(String[] connection, String[] connectionType) {
        String strcon;
        Set<String> set = new HashSet<>(Arrays.asList(connection));

// تبدیل دوباره به آرایه
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
            }//            Microservice.ConnectionType type = pair.getKey();
        }
    }

    @Override
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();

        String microserviceName;
        microserviceName = orderRole + orderRoleCounter++;
        Microservice OrderService = null;
        try {
            OrderService = new Microservice(
                    getPort(),
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
                    new Pair<>(Microservice.Pattern.EventSourcing.toString(), Microservice.Role.EventOrderService.toString()),
                    new EventSourcingStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(OrderService);
///////////////////////////////////////////////////////////////////////////////////////////////
        microserviceName = shippingRole + shippingRoleCounter++;
        Microservice ShippingService = null;
        try {
            ShippingService = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/append_event"),
                            new Pair<>(Microservice.ConnectionType.GET, "/rollback"),
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.EventSourcing.toString(), Microservice.Role.EventShippingService.toString()),
                    new EventSourcingStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(ShippingService);

        OrderService.setUsageMemory();
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
            case "EventOrderService":
                n = 10; // تعداد Builderها
                builders = new Builder[n];

                // creating the OrderController class
                builders[0] = OrderServiceClass(microservice, microserviceName, connections, 0);
                OrderServiceMethods(builders[0], 0, "OrderServiceController");

                //creating KafkaConfig.java
                builders[1] = OrderServiceClass(microservice, microserviceName, connections, 1);
                OrderServiceMethods(builders[1], 1, "KafkaConfig");

                //creating OrderServiceApplication
                builders[2] = OrderServiceClass(microservice, microserviceName, connections, 2);
                OrderServiceMethods(builders[2], 2, "OrderServiceApplication");

                //creating OrderService
                builders[3] = OrderServiceClass(microservice, microserviceName, connections, 3);
                OrderServiceMethods(builders[3], 3, "OrderService");

                //creating OrderEventRepository
                builders[4] = OrderServiceClass(microservice, microserviceName, connections, 4);


                //creating KafkaOrderEventPublisher
                builders[5] = OrderServiceClass(microservice, microserviceName, connections, 5);
                OrderServiceMethods(builders[5], 5, "KafkaOrderEventPublisher");

                //creating OrderEvent
                builders[6] = OrderServiceClass(microservice, microserviceName, connections, 6);

                //creating OrderResponse
                builders[7] = OrderServiceClass(microservice, microserviceName, connections, 7);

                // creating orderRequest
                builders[8] = OrderServiceClass(microservice, microserviceName, connections, 8);


                //creating OrderStatus
                builders[9] = OrderServiceClass(microservice, microserviceName, connections, 9);

                break;

            case "EventShippingService":
                n = 7; // تعداد Builderها
                builders = new Builder[n];

                // creating the ShippingController class
                builders[0] = ShippingServiceClass(microservice, microserviceName, connections, 0);
                OrderServiceMethods(builders[0], 0, "ShippingController");

                //creating KafkaConfig.java
                builders[1] = ShippingServiceClass(microservice, microserviceName, connections, 1);
                ShippingServiceMethods(builders[1], 1, "KafkaConfig");

                //creating OrderStatus
                builders[2] = ShippingServiceClass(microservice, microserviceName, connections, 2);

                //creating OrderEvent
                builders[3] = ShippingServiceClass(microservice, microserviceName, connections, 3);

                //creating ShippingEvent
                builders[4] = ShippingServiceClass(microservice, microserviceName, connections, 4);

                //creating ShippingEventRepository
                builders[5] = ShippingServiceClass(microservice, microserviceName, connections, 5);

                //creating ShippingServiceApplication
                builders[6] = ShippingServiceClass(microservice, microserviceName, connections, 6);
                ShippingServiceMethods(builders[6], 6, "ShippingServiceApplication");

                break;
        }
        return builders;
    }


    /* ************************************************************
     ***************************************************************
     * ************************************************************* */

    private void OrderServiceMethods(Builder builder, int id, String name) {

        if (id == 0) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("ResponseEntity<OrderResponse>"), "placeOrder"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "@RequestBody", "OrderRequest "), "orderRequest")
                    },
                    new Pair[]{new Pair(Constants.Annotations.PostMapping, "/place")},
                    Constants.Keywords.NULL.toString(),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("try {\n" +
                                        "            OrderResponse orderResponse = orderService.placeAnOrder(orderRequest);\n" +
                                        "            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);\n" +
                                        "        } catch (Exception e) {\n" +
                                        "            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);\n" +
                                        "        }", " "), ""));
                    }}
            );

            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("ResponseEntity<OrderResponse>"), "confirmOrder"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "@PathVariable", "String"), " orderId")
                    },
                    new Pair[]{new Pair(Constants.Annotations.PutMapping, "/confirm/{orderId}")},
                    Constants.Keywords.NULL.toString(),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        new Pair<>(new Pair<>("try {\n" +
                                "            OrderResponse orderResponse = orderService.confirmOrder(orderId);", " "), "");
                        new Pair<>(new Pair<>("return new ResponseEntity<>(orderResponse, HttpStatus.OK);", " "), "");
                        new Pair<>(new Pair<>("} catch (Exception e) {", " "), "");
                        new Pair<>(new Pair<>(" return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);\n" +
                                "        }", " "), "");
                    }}
            );
        } else if (id == 1) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("NewTopic"), " createTopic"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "@Bean", ""), "")
                    },
                    new Pair[]{},
                    Constants.Keywords.AddNewType("new NewTopic(topicName,3,(short)1);"),
                    null
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
                    varMethod);
        } else if (id == 3) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("OrderResponse"), " placeAnOrder"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "", "OrderRequest"), " orderRequest")
                    },
                    new Pair[]{},
                    Constants.Keywords.AddNewType("new OrderResponse(orderId, OrderStatus.CREATED);"),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("String orderId = UUID.randomUUID().toString().split(\"-\")[0];\n" +
                                        "        orderRequest.setOrderId(orderId);\n" +
                                        "        //do request validation and real business logic\n" +
                                        "        OrderEvent event = new OrderEvent(orderId, OrderStatus.CREATED, \"Order created successfully.\", LocalDateTime.now());\n" +
                                        "        saveAndPublishOrderEvent(event);", " "), ""));
                    }}
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("OrderResponse"), " confirmOrder"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "", "String"), " orderId")
                    },
                    new Pair[]{},
                    Constants.Keywords.AddNewType("new OrderResponse(orderId, OrderStatus.CONFIRMED);"),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("OrderEvent event = new OrderEvent(orderId, OrderStatus.CONFIRMED, \"Order confirmed successfully.\", LocalDateTime.now());\n" +
                                        "        saveAndPublishOrderEvent(event);", " "), ""));
                    }}
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PRIVATE,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("void"), " saveAndPublishOrderEvent"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "", "OrderEvent"), " event")
                    },
                    new Pair[]{},
                    Constants.Keywords.NULL.toString(),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("repository.save(event);\n" +
                                        "        eventPublisher.sendOrderEvent(event);", " "), ""));
                    }}
            );
        } else if (id == 5) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.VOID, " sendOrderEvent"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "", "OrderEvent"), " orderEvent")
                    },
                    new Pair[]{},
                    Constants.Keywords.NULL.toString(),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("kafkaTemplate.send(topicName, orderEvent.getOrderId(), orderEvent);", " "), ""));
                    }}
            );
        }

    }

    private void ShippingServiceMethods(Builder builder, int id, String name) {

        if (id == 0) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("ResponseEntity<String>"), " shipOrder"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "@PathVariable", "String  "), "orderId")
                    },
                    new Pair[]{new Pair(Constants.Annotations.PostMapping, "/{orderId}/ship")},
                    Constants.Keywords.AddNewType("ResponseEntity.ok(\"Order shipped successfully.\");"),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("shippingEventService.shipOrder(orderId);", " "), ""));
                    }}
            );
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("ResponseEntity<String>"), " deliverOrder"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "@PathVariable", "String  "), "orderId")
                    },
                    new Pair[]{new Pair(Constants.Annotations.PostMapping, "//{orderId}/deliver")},
                    Constants.Keywords.AddNewType("ResponseEntity.ok(\"Order delivered successfully.\");"),
                    new ArrayList<Pair<Pair<String, String>, String>>() {{
                        add(new Pair<>(
                                new Pair<>("shippingEventService.deliverOrder(orderId);", " "), ""));
                    }}
            );

        } else if (id == 1) {
            setMethodContent(
                    builder,
                    Constants.AccessLevel.PUBLIC,
                    false,
                    false,
                    new Pair(Constants.ReturnType.AddNewType("NewTopic"), " createTopic"),
                    new Pair[]{new Pair<>(Constants.VariableTypeRequestBody.AddNewType(
                            "@Bean", ""), "")
                    },
                    new Pair[]{},
                    Constants.Keywords.AddNewType("new NewTopic(topicName,3,(short)1);"),
                    null
            );
        } else if (id == 6) {
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
        } }

/* ************************************************************
***************************************************************
* ************************************************************* */
private Builder OrderServiceClass(Microservice microservice, String microserviceName, String[] connections, int id) {
    if (id == 0) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                microserviceName+"Controller",
                                null,
                                "",
                                new ConstructorGenerator[]{},
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.RequestMapping, "api/v1/orders" + microserviceName),
                                        new Pair<>(Constants.Annotations.RestController, "")
                                },
                                new String[]{"import microservice.eventSourcing.demo.API.OrderRequest;\n" +
                                        "import microservice.eventSourcing.demo.API.OrderResponse;\n" +
                                        "import microservice.eventSourcing.demo.API.OrderService;\n" +
                                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                                        "import org.springframework.http.HttpStatus;\n" +
                                        "import org.springframework.http.ResponseEntity;\n" +
                                        "import org.springframework.web.bind.annotation.*;"
                                }
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>("OrderService", " orderService"),
                                        "",
                                        new Pair[] {
                                                new Pair<>(Constants.Annotations.Autowired, "")},
                                        false,
                                        false))
                                .setContent(new POMGenerator(
                                        microserviceName, Main.getPath2(),"event-sourcing"))
                                .setContent(new ConfigFileGenerator(
                                        microserviceName,
                                        Main.getPath2(),microservice.getPort(),"event-sourcing"))
                                .setContent(new EventSourcingYMLGenerator(
                                        microserviceName,
                                        Main.getPath2(),"event-sourcing",microservice.getPort(),null))
                );}

    else if (id == 1) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "KafkaConfig",
                                null,
                                "",
                                new ConstructorGenerator[]{},
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.Configuration, "")
                                },
                                new String[]{"import org.apache.kafka.clients.admin.NewTopic;\n" +
                                        "import org.apache.kafka.common.internals.Topic;\n" +
                                        "import org.springframework.beans.factory.annotation.Value;\n" +
                                        "import org.springframework.context.annotation.Bean;\n" +
                                        "import org.springframework.context.annotation.Configuration;"
                                }
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>("String", " topicName"),
                                        "",
                                        new Pair[] {
                                                new Pair<>(Constants.Annotations.Value, "${order.event.topicName}")},
                                        false,
                                        false))
                );}

    else if (id == 2) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "OrderServiceApplication",
                                null,
                                "",
                                null,
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.SpringBootApplication,"")},

                                new String[]{"import org.springframework.boot.SpringApplication;\n" +
                                        "import org.springframework.boot.autoconfigure.SpringBootApplication;\n"}
                        ));
    }
    else if (id ==3) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "OrderService",
                                null,
                                "",
                                null,
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.Service,"")
                                },

                                new String[]{"import microservice.eventSourcing.demo.API.OrderStatus;\n" +
                                        "import microservice.eventSourcing.demo.API.OrderRequest;\n" +
                                        "import microservice.eventSourcing.demo.API.OrderResponse;\n" +
                                        "import microservice.eventSourcing.demo.API.OrderEvent;\n" +
                                        "import microservice.eventSourcing.demo.API.KafkaOrderEventPublisher;\n" +
                                        "import microservice.eventSourcing.demo.API.OrderEventRepository;\n" +
                                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                                        "import org.springframework.stereotype.Service;\n"+
                                        "import java.time.LocalDateTime;\n" +
                                        "import java.util.UUID;"}
                        )
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("OrderEventRepository", " repository"),
                                                "",
                                                new Pair[] {
                                                        new Pair<>(Constants.Annotations.Autowired,"")
                                                },
                                                false,
                                                false)
                                )
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("KafkaOrderEventPublisher", " eventPublisher"),
                                                "",
                                                new Pair[] {
                                                        new Pair<>(Constants.Annotations.Autowired,"")
                                                },
                                                false,
                                                false)
                                ));
    }
    else if (id ==4) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.INTERFACE,
                                "OrderEventRepository",
                                null,
                                "MongoRepository<OrderEvent,String>",
                                null,
                                new Pair[]{ },

                                new String[]{"import microservice.eventSourcing.demo.API.OrderEvent;\n" +
                                        "import org.springframework.data.mongodb.repository.MongoRepository;"}
                        ));

    }
    else if (id ==5) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "KafkaOrderEventPublisher",
                                null,
                                "",
                                new ConstructorGenerator[]{},
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.Component, "")
                                },
                                new String[]{"import microservice.eventSourcing.demo.API.OrderEvent;\n" +
                                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                                        "import org.springframework.beans.factory.annotation.Value;\n" +
                                        "import org.springframework.kafka.core.KafkaTemplate;\n" +
                                        "import org.springframework.stereotype.Component;"
                                }
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>("KafkaTemplate<String, OrderEvent>", " kafkaTemplate"),
                                        "",
                                        new Pair[] {
                                                new Pair<>(Constants.Annotations.Autowired, "")},
                                        false,
                                        false))

            .setContent(
                    new VariableGenerator(
                            Constants.AccessLevel.PRIVATE,
                            false,
                            false,
                            new Pair<>("String", " topicName"),
                            "",
                            new Pair[] {
                                    new Pair<>(Constants.Annotations.Value, "${order.event.topicName}")},
                            false,
                            false)
                    ));
    }
    else if (id == 6) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")

                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "OrderEvent",
                                null,
                                "",
                                new ConstructorGenerator[]{
                                        new ConstructorGenerator(
                                                Constants.AccessLevel.PUBLIC,
                                                "OrderEvent",
                                                null,
                                                new Pair[] {
                                                        new Pair<>(Constants.Annotations.setContent(""), "String orderId"),
                                                        new Pair<>(Constants.Annotations.setContent(""), "OrderStatus status"),
                                                        new Pair<>(Constants.Annotations.setContent(""), "String details"),
                                                        new Pair<>(Constants.Annotations.setContent(""), "LocalDateTime eventTimestamp")
                                                },
                                                null,
                                                new ArrayList<Pair<String, String>>() {{
                                                    add(new Pair<>("this.orderId", " orderId"));
                                                    add(new Pair<>("this.status", " status"));
                                                    add(new Pair<>("this.details", " details"));
                                                    add(new Pair<>("this.eventTimestamp", " eventTimestamp"));}})
                                },
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.Data, ""),
                                        new Pair<>(Constants.Annotations.AllArgsConstructor, ""),
                                        new Pair<>(Constants.Annotations.NoArgsConstructor, ""),
                                        new Pair<>(Constants.Annotations.Document, "collection = {OrderEvents}"),
                                },
                                new String[]{"import microservice.eventSourcing.demo.API.OrderStatus;\n" +
                                        "import lombok.AllArgsConstructor;\n" +
                                        "import lombok.Data;\n" +
                                        "import lombok.NoArgsConstructor;\n" +
                                        "import org.springframework.data.annotation.Id;\n" +
                                        "import org.springframework.data.mongodb.core.mapping.Document;\n"+
                                        "import java.time.LocalDateTime;\n"
                                }
                        ).setContent(
                                new VariableGenerator(
                                        Constants.AccessLevel.PRIVATE,
                                        false,
                                        false,
                                        new Pair<>("String", " id"),
                                        "",
                                        new Pair[] {
                                                new Pair<>(Constants.Annotations.Id, "")},
                                        false,
                                        false))
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("String", " orderId"),
                                                "",
                                                new Pair[] {},
                                                false,
                                                false))
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("OrderStatus", " status"),
                                                "",
                                                new Pair[] {},
                                                false,
                                                false))
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("String", " details"),
                                                "",
                                                new Pair[] {},
                                                false,
                                                false))
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("LocalDateTime", " eventTimestamp"),
                                                "",
                                                new Pair[] {},
                                                false,
                                                false))
                );}
    else if (id ==7) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "OrderResponse",
                                null,
                                "",
                                null,
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.Data,""),
                                        new Pair<>(Constants.Annotations.AllArgsConstructor,""),
                                        new Pair<>(Constants.Annotations.NoArgsConstructor,"")
                                },

                                new String[]{"import microservice.eventSourcing.demo.API.OrderStatus;\n" +
                                        "import lombok.AllArgsConstructor;\n" +
                                        "import lombok.Data;\n" +
                                        "import lombok.NoArgsConstructor;"}
                        )
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("String", " orderId"),
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
                                                new Pair<>("OrderStatus", " status"),
                                                "",
                                                new Pair[] {},
                                                false,
                                                false)
                                ));}
    else if (id ==8) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.CLASS,
                                "OrderRequest",
                                null,
                                "",
                                null,
                                new Pair[]{
                                        new Pair<>(Constants.Annotations.Data,""),
                                        new Pair<>(Constants.Annotations.AllArgsConstructor,""),
                                        new Pair<>(Constants.Annotations.NoArgsConstructor,"")
                                },

                                new String[]{"import lombok.AllArgsConstructor;\n" +
                                        "import lombok.Data;\n" +
                                        "import lombok.NoArgsConstructor;"}
                        )
                                .setContent(
                                        new VariableGenerator(
                                                Constants.AccessLevel.PRIVATE,
                                                false,
                                                false,
                                                new Pair<>("String", " orderId"),
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
                                                new Pair<>("String", " userId"),
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
                                                new Pair<>("int", " qty"),
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
                                                new Pair<>("double", " price"),
                                                "",
                                                new Pair[] {},
                                                false,
                                                false)
                                )
                );}
    else if (id == 9) {
        System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
        return new Builder("microservice." + "eventSourcing" + ".demo.API")
                .setContent(
                        new ClassGenerator(
                                Constants.AccessLevel.PUBLIC,
                                Constants.ElementType.ENUM,
                                "OrderStatus",
                                null,
                                "",
                                null,
                                new Pair[]{},

                                new String[]{}
                        )
                .setContent(
                        new BodyGenerator(
                                null,
                                null,
                                new ArrayList<Pair<Pair<String,String>,String>>() {{
                                    add(new Pair<>(new Pair("CREATED,\n" +
                                            "    CONFIRMED,\n" +
                                            "    SHIPPED,\n" +
                                            "    DELIVERED;",""), ""));
                                }}

                        ))
                );
    }
    else {return null;}
}
    /* ************************************************************
     ***************************************************************
     * ************************************************************* */
 private Builder ShippingServiceClass(Microservice microservice, String microserviceName, String[] connections, int id)
 {
     if (id == 0) {
         System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
         return new Builder("microservice." + "eventSourcing" + ".demo.API")
                 .setContent(
                         new ClassGenerator(
                                 Constants.AccessLevel.PUBLIC,
                                 Constants.ElementType.CLASS,
                                 microserviceName+"Controller",
                                 null,
                                 "",
                                 new ConstructorGenerator[]{},
                                 new Pair[]{
                                         new Pair<>(Constants.Annotations.RequestMapping, "api/v1/shipping" + microserviceName),
                                         new Pair<>(Constants.Annotations.RestController, "")
                                 },
                                 new String[]{"import microservice.eventSourcing.demo.API.OrderRequest;\n" +
                                         "import microservice.eventSourcing.demo.API.OrderResponse;\n" +
                                         "import microservice.eventSourcing.demo.API.OrderService;\n" +
                                         "import org.springframework.beans.factory.annotation.Autowired;\n" +
                                         "import org.springframework.http.HttpStatus;\n" +
                                         "import org.springframework.http.ResponseEntity;\n" +
                                         "import org.springframework.web.bind.annotation.*;"
                                 }
                         ).setContent(
                                 new VariableGenerator(
                                         Constants.AccessLevel.PRIVATE,
                                         false,
                                         false,
                                         new Pair<>("ShippingEventService", " shippingEventService"),
                                         "",
                                         new Pair[] {
                                                 new Pair<>(Constants.Annotations.Autowired, "")},
                                         false,
                                         false))
                 );}

     else if (id == 1) {

         System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
         return new Builder("microservice." + "eventSourcing" + ".demo.API")
                 .setContent(
                         new ClassGenerator(
                                 Constants.AccessLevel.PUBLIC,
                                 Constants.ElementType.CLASS,
                                 "KafkaConfig",
                                 null,
                                 "",
                                 new ConstructorGenerator[]{},
                                 new Pair[]{
                                         new Pair<>(Constants.Annotations.Configuration, "")
                                 },
                                 new String[]{"import org.apache.kafka.clients.admin.NewTopic;\n" +
                                         "import org.springframework.beans.factory.annotation.Value;\n" +
                                         "import org.springframework.context.annotation.Bean;\n" +
                                         "import org.springframework.context.annotation.Configuration;"
                                 }
                         ).setContent(
                                 new VariableGenerator(
                                         Constants.AccessLevel.PRIVATE,
                                         false,
                                         false,
                                         new Pair<>("String", " topicName"),
                                         "",
                                         new Pair[] {
                                                 new Pair<>(Constants.Annotations.Value, "${shipping.event.topicName}")},
                                         false,
                                         false))
                 );}
     else if (id == 2) {
         System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
         return new Builder("microservice." + "eventSourcing" + ".demo.API")
                 .setContent(
                         new ClassGenerator(
                                 Constants.AccessLevel.PUBLIC,
                                 Constants.ElementType.ENUM,
                                 "OrderStatus",
                                 null,
                                 "",
                                 null,
                                 new Pair[]{},

                                 new String[]{}
                         ))
                 .setContent(
                         new BodyGenerator(
                                 null,
                                 null,
                                 new ArrayList<Pair<Pair<String,String>,String>>() {{
                                     add(new Pair<>(new Pair("CREATED,\n" +
                                             "    CONFIRMED,\n" +
                                             "    SHIPPED,\n" +
                                             "    DELIVERED;",""), ""));
                                 }}

                         )
                 );
     }
     else if (id == 3) {
         System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
         return new Builder("microservice." + "eventSourcing" + ".demo.API")
                 .setContent(
                         new ClassGenerator(
                                 Constants.AccessLevel.PUBLIC,
                                 Constants.ElementType.CLASS,
                                 "OrderEvent",
                                 null,
                                 "",
                                 new ConstructorGenerator[]{
                                         new ConstructorGenerator(
                                                 Constants.AccessLevel.PUBLIC,
                                                 "OrderEvent",
                                                 null,
                                                 new Pair[] {
                                                         new Pair<>(Constants.Annotations.setContent(""), "String orderId"),
                                                         new Pair<>(Constants.Annotations.setContent(""), "OrderStatus status"),
                                                         new Pair<>(Constants.Annotations.setContent(""), "String details"),
                                                         new Pair<>(Constants.Annotations.setContent(""), "LocalDateTime eventTimestamp")
                                                 },
                                                 null,
                                                 new ArrayList<Pair<String, String>>() {{
                                                     add(new Pair<>("this.orderId", "orderId"));
                                                     add(new Pair<>("this.status", "status"));
                                                     add(new Pair<>("this.details", "details"));
                                                     add(new Pair<>("this.eventTimestamp", "eventTimestamp"));}})
                                 },
                                 new Pair[]{
                                         new Pair<>(Constants.Annotations.Data, ""),
                                         new Pair<>(Constants.Annotations.AllArgsConstructor, ""),
                                         new Pair<>(Constants.Annotations.NoArgsConstructor, "")
                                 },
                                 new String[]{"import microservice.eventSourcing.demo.API.OrderStatus;\n" +
                                         "import lombok.AllArgsConstructor;\n" +
                                         "import lombok.Data;\n" +
                                         "import lombok.NoArgsConstructor;\n" +
                                         "import java.time.LocalDateTime;\n"
                                 }
                         ).setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("String", " id"),
                                                 "",
                                                 new Pair[] {
                                                         new Pair<>(Constants.Annotations.Id, "")},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("String", " orderId"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("OrderStatus", " status"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("String", " details"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("LocalDateTime", " eventTimestamp"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                 );}
     else if (id == 4) {
         System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
         return new Builder("microservice." + "eventSourcing" + ".demo.API")
                 .setContent(
                         new ClassGenerator(
                                 Constants.AccessLevel.PUBLIC,
                                 Constants.ElementType.CLASS,
                                 "ShippingEvent",
                                 null,
                                 "",
                                 new ConstructorGenerator[]{
                                         new ConstructorGenerator(
                                                 Constants.AccessLevel.PUBLIC,
                                                 "ShippingEvent",
                                                 null,
                                                 new Pair[] {
                                                         new Pair<>(Constants.Annotations.setContent(""), "String orderId"),
                                                         new Pair<>(Constants.Annotations.setContent(""), "OrderStatus status"),
                                                         new Pair<>(Constants.Annotations.setContent(""), "String details"),
                                                         new Pair<>(Constants.Annotations.setContent(""), "LocalDateTime eventTimestamp")
                                                 },
                                                 null,
                                                 new ArrayList<Pair<String, String>>() {{
                                                     add(new Pair<>("this.orderId", "orderId"));
                                                     add(new Pair<>("this.status", "status"));
                                                     add(new Pair<>("this.details", "details"));
                                                     add(new Pair<>("this.eventTimestamp", "eventTimestamp"));}})
                                 },
                                 new Pair[]{
                                         new Pair<>(Constants.Annotations.Data, ""),
                                         new Pair<>(Constants.Annotations.AllArgsConstructor, ""),
                                         new Pair<>(Constants.Annotations.NoArgsConstructor, ""),
                                         new Pair<>(Constants.Annotations.Document, "collection = \"OrderEvents\""),
                                 },
                                 new String[]{"import microservice.eventSourcing.demo.API.OrderStatus;\n" +
                                         "import lombok.AllArgsConstructor;\n" +
                                         "import lombok.Data;\n" +
                                         "import lombok.NoArgsConstructor;\n" +
                                         "import org.springframework.data.mongodb.core.mapping.Document;\n"+
                                         "import java.time.LocalDateTime;\n"
                                 }
                         ).setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("String", " id"),
                                                 "",
                                                 new Pair[] {
                                                         new Pair<>(Constants.Annotations.Id, "")},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("String", " orderId"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("OrderStatus", " status"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("String", " details"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                                 .setContent(
                                         new VariableGenerator(
                                                 Constants.AccessLevel.PRIVATE,
                                                 false,
                                                 false,
                                                 new Pair<>("LocalDateTime", " eventTimestamp"),
                                                 "",
                                                 new Pair[] {},
                                                 false,
                                                 false))
                 );}
     else if (id ==5) {
         System.out.println("microserviceNameRole: "+ microservice.getRole()+"and id =" +id+"\n");
         return new Builder("microservice." + "eventSourcing" + ".demo.API")
                 .setContent(
                         new ClassGenerator(
                                 Constants.AccessLevel.PUBLIC,
                                 Constants.ElementType.INTERFACE,
                                 "ShippingEventRepository",
                                 null,
                                 "MongoRepository<OrderEvent,String>",
                                 null,
                                 new Pair[]{ },

                                 new String[]{"import microservice.eventSourcing.demo.API.OrderEvent;\n" +
                                         "import org.springframework.data.mongodb.repository.MongoRepository;"}
                         ));

     }
     else if (id == 6) {
         return new Builder("microservice." + "eventSourcing" + ".demo.API")
                 .setContent(
                         new ClassGenerator(
                                 Constants.AccessLevel.PUBLIC,
                                 Constants.ElementType.CLASS,
                                 "ShippingServiceApplication",
                                 null,
                                 "",
                                 null,
                                 new Pair[]{
                                         new Pair<>(Constants.Annotations.SpringBootApplication,"")},

                                 new String[]{"import org.springframework.boot.SpringApplication;\n" +
                                         "import org.springframework.boot.autoconfigure.SpringBootApplication;\n"}
                         ));
     }
     return null;
 }

}
