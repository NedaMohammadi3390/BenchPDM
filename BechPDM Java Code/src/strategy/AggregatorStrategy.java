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


    // درون متد matrixFiler مشخص میشه که چه میکروسرویسهاییی به چه تعداد وجود دارد و هر یک چه access point هایی درون خودشون تعریف کردند و یا اینکه به چه میکروسرویس هایی وصل هستند. و هر میکروسرویس را به matrices اضافه میکنیم.
    @Override
    public ArrayList<Microservice> matrixFiller( ) {

        ArrayList<Microservice> matrices = new ArrayList<>();
        int workerNumber = random.nextInt(5) + 2;// تعداد ورکرها را به صورت تصادفی تعیین میکنیم. یک عدد تصادفی بین ۲ تا ۶ تولید می شود.

        String microserviceName = aggregatorRole + aggregatorCounter++;// ایجاد یک نام متمایز برای aggregator تا در صورتی که از این الگو بیشتر از یکی خواستیم تولید کنیم هدها نام های متمایزی داشته باشند.
        Microservice aggregator = null;
        try {
            aggregator = new Microservice(
                    id++,
                    microserviceName,
                    URIGenerator(microserviceName),
                    new Pair[]{},
                    false,
                    creationTime(),// برای ما creationTime مایکروسرویس های درون یک الگو مهم هست.
                    new Pair<>(Microservice.Pattern.Aggregator.toString(), Microservice.Role.Aggregator.toString()),
                    new AggregatorStrategy()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        matrices.add(aggregator);
        /////////////////////////////////////////////////////
// در اینجا میخواهیم تک تک میکروسرویس های ورکر را به matrics اضافه کنیم.
        for (int i = 0; i < workerNumber; i++) {
            microserviceName = workerRole + workerCounter++;// یک اسم متمایز را برای ورکر تولید میکنه.
            int linksNumber = random.nextInt(6) + 1;// در اینجا بصورت رندوم مشخص میکنیم که چند تا api درون مایکروسرویس worker وجود داره. در واقع تعداد access point ها به این مایکروسرویس ورکر رو تعیین میکنیم.
            Pair[] links = new Pair[linksNumber];// به تعداد access point ها، جفت مقدار تعریف میکنیم.
            for (int j = 0; j < links.length; j++) {
//                تمامی access point های درون ورکر از نوع GET هستند
                links[j] = new Pair(Microservice.ConnectionType.GET, String.valueOf(j));
            }
            // (int id, String microserviceName, String URI, Pair<ConnectionType, String>[] connectionTypes,
            //                        boolean duplicate, String creationTime, Pair<String, String> role, Strategy strategy)
            Microservice worker = null;
            try {
                worker = new Microservice(
                        id++,
                        microserviceName,
                        URIGenerator(microserviceName),
                        links, // تمامی access point های میکروسرویس ورکر رو معرفی میکنیم. در اینجا چندین GET
                        false,
                        creationTime(),
                        new Pair<>(Microservice.Pattern.Aggregator.toString(), Microservice.Role.Worker.toString()),
                        new AggregatorStrategy()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int j = 0; j < links.length; j++) {// تنها aggregator به access point های درون یک worker میتونه وصل شود.
                // بنابراین در اینجا به تعداد لینک ها (access point) هایی که درون ورکر تعریف کردیم، برای aggregator، setConnection تعریف میکنیم و میگیم که agg به این ورکر وصل هست.
                aggregator.setConnections(new Pair<>(Microservice.ConnectionType.GET, worker.getURI()));
                aggregator.setConnections(new Pair<>(Microservice.ConnectionType.POST, worker.getURI()));
                aggregator.setConnections(new Pair<>(Microservice.ConnectionType.PUT, worker.getURI()));
                aggregator.setConnections(new Pair<>(Microservice.ConnectionType.DELETE, worker.getURI()));
                aggregator.setUsageMemory();

            }
            matrices.add(worker);
        }
        aggregator.setUsageCPU();
        return matrices;
    }
    //**************************************************************************************************************************************
    @Override
    public Builder fileFiller(String role,
                              String microserviceName,
                              String[] connections, //اینجا uri میکروسرویس های ورکر که درون aggregator فراخوانی میشوند رو میاره. توجه داشته باش که هر ورکر به تعداد api GET که درون خودش داره در اینجا ادرسش میاد
                              // بنابراین اگر ۴ تا ورکر داشته باشیم که هر کدوم مثالا دو تا api GET درونشون باشه، ۸ تا uri باید درونن aggregator تعریف شود.
                              Pair<Microservice.ConnectionType, String>[] connectionTypes) {// یعنی چه api هایی درون خودش داره
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
            case "Aggregator":
                builder = aggregatorClass(microserviceName);
                break;
            case "Worker":
                builder = workerClass(microserviceName, connections);
                setMethods(builder, connectionTypes);
                break;
        }
        return builder;
    }

    private Builder aggregatorClass(String microserviceName) {// در اینجا علاوه بر نام میکروسرویس باید connections رو هم به عنوان ورودی پاس بده.
        return new Builder("microservice." + "aggregator" + ".demo.API")
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
                                        false,// میتونیم براشون getter و setter  رو فعال کنیم.
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
                                        new Pair[]{ new Pair<>(Constants.Annotations.Autowired, "")},
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
//        for (int i = 0; i < connectionTypes.length; i++) {
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
