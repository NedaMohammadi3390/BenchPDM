package strategy;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.generator.MethodGenerator;
import data_structure.Microservice;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public abstract class Strategy {
    public static int id = 0;
    private static int port = 9010;
    private static int time = -1;
    public Random random = new Random();
    private int firsttime=0;



    public abstract ArrayList<Microservice> matrixFiller();

    public abstract Builder fileFiller(String role,
                                       String microserviceName,
                                       String[] connections,
                                       Pair<Microservice.ConnectionType, String>[] connectionTypes);

    protected String URIGenerator(String microserviceName) {
        String uri = "http://localhost:" + port + "/api/" + microserviceName;
        port++;
        return uri;
    }

    protected String creationTime( ) {

//        if (firsttime==0)  time=-1;

        time++;
//        firsttime=1;
//      //  System.out.println("creation time is: "+ String.valueOf(time));
        return String.valueOf(time);
    }

    protected Builder setMethodContent(Builder builder,
                                       Constants.AccessLevel accessLevel,
                                       boolean isStatic,
                                       boolean isFinal,
                                       Pair<Constants.ReturnType, String> returnTypeName,
                                       Pair[] params,
                                       Pair<Constants.Annotations, String>[] annotationsContentPair,
                                       String returnStatement,
                                       ArrayList<Pair<Pair<String,String>,String>> bodyMethod) {
        return builder.getBuilderClass().setContent(
                new MethodGenerator(
                        accessLevel,
                        isStatic,
                        isFinal,
                        returnTypeName,
                        params,
                        null,
                        annotationsContentPair,
                        returnStatement,
                        bodyMethod
                ));
    }
}
