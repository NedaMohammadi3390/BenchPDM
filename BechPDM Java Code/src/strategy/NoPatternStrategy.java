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
import java.util.*;

public class NoPatternStrategy extends Strategy {

    public static int worker1Counter = 0;
    public static int worker2Counter = 0;
    public static int clientCounter=0;

    String clientRole = "Client";
    String worker1Role = "worker";
    String worker2Role = "Worker";

    Microservice worker1 = null;
    Pair<Microservice.ConnectionType, String>[] conworker1;
    Microservice worker2 = null;
    Pair<Microservice.ConnectionType, String>[] conworker2;
    Microservice client = null;
    Pair<Microservice.ConnectionType, String>[] conclient;
    Map<String, List<Microservice>> roleBasedServices_client = new HashMap<>();
    Map<String, List<Microservice>> roleBasedServices_worker = new HashMap<>();
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
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Pair<Microservice.ConnectionType, String>[]  generatePair() {
        Pair<Microservice.ConnectionType, String>[] connectionPair = new Pair[0];
        // List of numbers
        int[] numbers = {2, 3, 5, 7, 6, 10, 14, 15, 21, 70, 30, 210, 35, 42, 105};

        // Create an instance of Random
        Random random = new Random();

        // Generate a random index within the bounds of the array
        //int randomIndex = random.nextInt(numbers.length);
        int randomIndex = 2;
        // Retrieve the number at the random index
        int randomNumber = numbers[randomIndex];
        if (randomNumber == 2) {
            connectionPair = new Pair[]{
                    new Pair<>(Microservice.ConnectionType.GET, "/receive")
            };
        } else if (randomNumber == 3) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.POST, "/send")};
        } else if (randomNumber == 5) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.PUT, "/update")};
        } else if (randomNumber == 7) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        } else if (randomNumber == 6) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.POST, "/send")};
        } else if (randomNumber == 10) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.PUT, "/update")};
        } else if (randomNumber == 14) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        } else if (randomNumber == 15) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.POST, "/send"), new Pair<>(Microservice.ConnectionType.PUT, "/update")};
        } else if (randomNumber == 21) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        } else if (randomNumber == 70) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.PUT, "/update"),
                    new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        } else if (randomNumber == 30) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.POST, "/send"),
                    new Pair<>(Microservice.ConnectionType.PUT, "/update")};
        } else if (randomNumber == 210) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.POST, "/send"),
                    new Pair<>(Microservice.ConnectionType.PUT, "/update"), new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        } else if (randomNumber == 35) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.PUT, "/update"), new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        } else if (randomNumber == 42) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.GET, "/receive"), new Pair<>(Microservice.ConnectionType.POST, "/send"),
                    new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        } else if (randomNumber == 105) {
            connectionPair = new Pair[]{new Pair<>(Microservice.ConnectionType.POST, "/send"), new Pair<>(Microservice.ConnectionType.PUT, "/update"),
                    new Pair<>(Microservice.ConnectionType.DELETE, "/delete")};
        }
        return connectionPair;
    }




    @Override
    public ArrayList<Microservice> matrixFiller( ) {
        ArrayList<Microservice> matrices = new ArrayList<>();
        int workerNumber1 = random.nextInt(5) + 1;
        String microserviceName;
        for (int i = 0; i < workerNumber1; i++) {

            microserviceName = worker1Role + worker1Counter++;

            conworker1 = generatePair();
            try {
                worker1 = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        conworker1,
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.NoPattern.toString(), Microservice.Role.Worker.toString()),
                        new NoPatternStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(worker1);
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int clientNumber = random.nextInt(6) + 3;
        for (int i = 0; i < clientNumber; i++) {
            microserviceName = clientRole + clientCounter++;

            conclient=generatePair();
            try {
                client = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        conclient,
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.NoPattern.toString(), Microservice.Role.Client.toString()),
                        new NoPatternStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matrices.add(client);
        }

        distinctingRoles(matrices);
        client.setUsageCPU();
        return matrices;
    }
     public void distinctingRoles( ArrayList<Microservice> matrices) {

        for (Microservice microservice : matrices) {
            String role = microservice.getRole().getValue();

            if (role.equals("Client")) {
                // Initialize the list if the role is encountered for the first time
                roleBasedServices_client.computeIfAbsent(role, k -> new ArrayList<>());

                // Add the Microservice to the corresponding role list
                roleBasedServices_client.get(role).add(microservice);
            } else if (role.equals("Worker")) {

                roleBasedServices_worker.computeIfAbsent(role, k -> new ArrayList<>());
                roleBasedServices_worker.get(role).add(microservice);
            }
        }

        //****************************
        for (Map.Entry<String, List<Microservice>> entryClient : roleBasedServices_client.entrySet()) {

            List<Microservice> microservices_client = entryClient.getValue(); // Get the list of Microservices


            for (Microservice client : microservices_client) {
                for (Map.Entry<String, List<Microservice>> entryworker : roleBasedServices_worker.entrySet()) {

                    List<Microservice> microservices_worker = entryworker.getValue(); // Get the list of Microservices


                    for (Microservice worker : microservices_worker) {
                        createConnection(worker,client);
                        createConnection(client,worker);
                    }
                }
            }
        }
    }


    //*************************************************************************************

    public void createConnection(Microservice subject1,Microservice subject3){
        Random random = new Random();


        List<Microservice.ConnectionType> connectionTypes = new ArrayList<>();
//        if (selectWorker) {
            for (Pair<Microservice.ConnectionType, String> pair : conworker1) {
                connectionTypes.add(pair.getKey());
            }

        Random random2 = new Random();

        // Shuffle the list to randomize the order
        Collections.shuffle(connectionTypes, random2);

        // Determine the size of the subset (e.g., 2)
        int subsetSize = random2.nextInt(connectionTypes.size()) + 1; // To ensure at least 1 element

        // Select a subset from the shuffled list
        List<Microservice.ConnectionType> subset = connectionTypes.subList(0, subsetSize);

        for (Microservice.ConnectionType su : subset) {
// Create a Pair for each connection type
            Pair<Microservice.ConnectionType, String> connection =
                    new Pair<>(su, subject1.getURI());

            // Set the connection for worker2
            subject3.setConnections(connection);
            subject3.setUsageMemory();

//            client.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker2.getURI()));
        }

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

        Builder  builder = createClass(microserviceName);
        setMethods(builder,connectionTypes);

        return builder;
    }


    private Builder createClass(String microserviceName) {
        return new Builder("microservice." + "NoPattern" + ".demo.API")
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
                                                null)
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
                        )
                );
    }

    private void setMethods(Builder builder, Pair<Microservice.ConnectionType, String>[] connectionTypes) {
        for (Pair<Microservice.ConnectionType, String> connection : connectionTypes) {
            switch (connection.getKey()) {
                case GET:
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
                    break;
                case POST:
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
                    break;
                case PUT:
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
                    break;
                case DELETE:
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
                    break;
                default:
                    System.out.println("Unknown connection type.");
                    break;
            }
        }
    }

}
