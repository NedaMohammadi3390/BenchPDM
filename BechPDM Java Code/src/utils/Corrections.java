package utils;

import content_generation.Constants;
import data_structure.Microservice;
import javafx.util.Pair;
import strategy.Strategy;

import java.io.File;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Corrections {
    public static boolean isCorrectPackage(String path) {
        if (!(new File(path).exists())) {
            throw new IllegalCharsetNameException("This package does not exists: \"" + path + "\"");
        }
        return true;
    }
    public static String correctPath(String path) {
        String[] paths = path.split("\\.");
        StringBuilder finalPath = new StringBuilder(paths[0] + "\\");
        for (int i = 1; i < paths.length; i++) {
            finalPath.append(paths[i]).append("\\");
        }
        return finalPath.toString();
    }

    public static String createParameters(Pair[] params) {
        StringBuilder parameter = new StringBuilder();
        String space = " ";
        String comma = "," + space;
        if (params != null) {
            for (Pair p : params) {
                parameter.append(p.getKey().toString())
                        .append(p.getValue().toString()).append(comma);
            }

            parameter.delete(parameter.lastIndexOf(","), parameter.length());
        }

        return parameter.toString();
    }

    public static String initiate(String name) {
        String semicolon = ";";
        String emptyLine = "\n";
        String space = " ";
        return "this." + name + space + "=" + space + name + semicolon + emptyLine;
    }

    public static String newsStatement(ArrayList<Pair<String, String>>  newsStatement){
        if (newsStatement == null) return "";
        StringBuilder sb = new StringBuilder();
        for (Pair p :
                newsStatement) {
            String s = p.getKey() + " = new " + p.getValue() + "();";
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    ///////////////////////////////////////////*******************
public static String createBodyMethod(ArrayList<Pair<Pair<String,String>,String>> newbody ){
    if (newbody == null) return "";
    StringBuilder sb = new StringBuilder();
    for (Pair<Pair<String,String>,String> input : newbody) {
        String s = input.getKey().getKey()+""+input.getKey().getValue()+ "" + input.getValue()+"";
        sb.append(s).append("\n");
    }
    return sb.toString();
}
    //////////////////////////////////////////////////////********************

    public static String createConnections(String[] connections) {
        if (connections == null || connections.length == 0) return "";
        StringBuilder URIs = new StringBuilder();

        Arrays.stream(connections).forEach(s -> URIs.append("connections.add(\"").append(s).append("\");\n"));

        return URIs.toString();
    }

    public static String initiate(Pair[] pairs) {

        if (pairs == null) return "";

        StringBuilder init = new StringBuilder();

        for (Pair pair : pairs) {

            init.append(initiate(pair.getValue().toString()));
        }

        return init.toString();
    }

    public static boolean isInvalid(String name) {
        if (containSpace(name)) {
            throw new IllegalCharsetNameException("Name must not contains space: \"" + name + "\"");
        } else if (startWithNumber(name)) {
            throw new IllegalCharsetNameException("Name must not start with numbers: \"" + name + "\"");
        }
        return false;
    }

    public static String annotationCreator(Pair<Constants.Annotations, String>[] annotationsContentPair) {
        if (annotationsContentPair == null) return "";

        StringBuilder annotation = new StringBuilder();
        for (Pair pair : annotationsContentPair) {
            annotation.append(pair.getKey());
            if (!pair.getValue().equals("")) {
                annotation.append("(path = ").append("\"").append(pair.getValue()).append("\"").append(")");
            }
            annotation.append("\n");
        }
        return annotation.toString();
    }

    public static String classAnnotationCreator(Pair<Constants.Annotations, String>[] annotationsContentPair) {
        if (annotationsContentPair == null) return "";

        StringBuilder annotation = new StringBuilder();
        for (Pair pair : annotationsContentPair) {
            annotation.append(pair.getKey());
            if (!pair.getValue().equals("")) {
                annotation.append("(").append("\"").append(pair.getValue()).append("\"").append(")");
            }
            annotation.append("\n");
        }
        return annotation.toString();
    }

    public static String tabCalculator(int tabCounter) {
        StringBuilder tab = new StringBuilder();
        for (int i = 0; i < tabCounter; i++) {
            tab.append("\t");
        }
        return tab.toString();
    }

    private static boolean containSpace(String name) {
        return name.contains(" ");
    }

    private static boolean startWithNumber(String name) {

        if (name.contains(".")) {

            String[] names = name.split("\\.");

            for (String n : names) {

                if (String.valueOf(n.charAt(0)).matches("\\d")) {
                    return true;
                }
            }
        }

        return String.valueOf(name.charAt(0)).matches("-?\\d+(\\.\\d+)?");

    }

    public static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
