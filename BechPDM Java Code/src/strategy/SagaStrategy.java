package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.*;
import content_generation.generator.CustomizedCodeFileGenerator.priorityQueu.ClientGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.saga.OrderServiceGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.saga.PaymentServiceGeneration;
import content_generation.generator.CustomizedCodeFileGenerator.saga.ShippingServiceGeneration;
import data_structure.Microservice;
import javafx.util.Pair;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SagaStrategy extends Strategy {
    public static int paymentCounter = 0;
    public static int orderCounter = 0;
    public static int shippingCounter = 0;

    String paymentRole = "Payment";
    String orderRole = "Order";
    String shippingRole = "Shipping";

    public static Pair<Microservice.ConnectionType, String>[] connectionTypes;
    public int i = 1;
    public int j = 1;
    ArrayList<Pair<Pair<String, String>, String>> list = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Getlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Putlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Postlist = new ArrayList<>();
    ArrayList<Pair<Pair<String, String>, String>> Deletelist = new ArrayList<>();


    @Override
    public ArrayList<Microservice> matrixFiller() {
        ArrayList<Microservice> matrices = new ArrayList<>();
        //payement role
            String microserviceName = paymentRole + paymentCounter++;
                Microservice payment = null;
                try {
                    payment = new Microservice(
                            getPort(),
                            id++,
                            microserviceName,
                            URIGenerator(microserviceName),
                            new Pair[]{
                                    new Pair<>(Microservice.ConnectionType.POST, "/payment-finished")
                            },
                            false,
                            creationTime(),
                            new Pair<>(Microservice.Pattern.Saga.toString(), Microservice.Role.Payment.toString()),
                            new SagaStrategy()
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                matrices.add(payment);
                //**//
         microserviceName = orderRole + orderCounter++;
        Microservice order = null;
        try {
            order = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/create-payment")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.Saga.toString(), Microservice.Role.Order.toString()),
                    new SagaStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(order);

        order.setConnections(new Pair<>(Microservice.ConnectionType.POST, payment.getURI()));
        payment.setConnections(new Pair<>(Microservice.ConnectionType.POST, order.getURI()));
        //**//
        microserviceName = shippingRole + shippingCounter++;
        Microservice shipping = null;
        try {
            shipping = new Microservice(
                    getPort(),
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{
                            new Pair<>(Microservice.ConnectionType.POST, "/product-shipped")
                    },
                    false,
                    creationTime(),
                    new Pair<>(Microservice.Pattern.Saga.toString(), Microservice.Role.Shipping.toString()),
                    new SagaStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(shipping);
        order.setConnections(new Pair<>(Microservice.ConnectionType.POST, shipping.getURI()));
        shipping.setConnections(new Pair<>(Microservice.ConnectionType.POST, order.getURI()));
        return matrices;
    }

    @Override
    public Builder[] fileFiller(Microservice microservice,
                                String role,
                                String microserviceName,
                                String[] connections, Pair<Microservice.ConnectionType,
            String>[] connectionTypes) {

        int n = 1; // تعداد Builderها
        Builder[] builders = new Builder[n];
        switch (role) {
            case "Payment":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 0);
                break;

            case "Order":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 1);
                break;

            case "Shipping":
                builders[0] = GenerationClass(microservice, microserviceName, connections, 2);
                break;
        }
        return builders;
    }

    /*******************************************************************
     * ******************************************************************
     * *******************************************************************/
    private Builder GenerationClass(Microservice microservice,
                                    String microserviceName,
                                    String[] connections, int id) {
        //payment service
        if (id == 0) {
            Builder builder = new Builder("microservice." + "saga" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "saga"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "saga"));

            builder.setContent(new PaymentServiceGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "saga/payment",
                    "/src/main/java/microservice/saga/demo/API", builder.getPackageName()));

            return builder;
        }
        //Order service
        if (id == 1) {
            Builder builder = new Builder("microservice." + "saga" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "saga"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "saga"));

            builder.setContent(new OrderServiceGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "saga/order",
                    "/src/main/java/microservice/saga/demo/API",
                    builder.getPackageName()));

            return builder;
        }
        if (id == 2) {
            Builder builder = new Builder("microservice." + "saga" + ".demo.API");
            builder.setContent(new POMGenerator(
                    microserviceName, Main.getPath2(), "saga"));

            builder.setContent(new ConfigFileGenerator(
                    microserviceName,
                    Main.getPath2(),
                    microservice.getPort(),
                    "saga"));

            builder.setContent(new ShippingServiceGeneration(
                    microserviceName,
                    Main.getPath2(),
                    "saga/shipping",
                    "/src/main/java/microservice/saga/demo/API",
                    builder.getPackageName()));

            return builder;
        }
        else {
            return null;
        }

    }
}