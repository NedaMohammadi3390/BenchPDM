package content_generation.generator;

import content_generation.Builder;
import content_generation.IGenerate;
import javafx.util.Pair;
import utils.Corrections;


import java.util.ArrayList;

public class BodyGenerator extends Builder implements IGenerate {
    private final  ArrayList<Pair<Pair<String,String>,String>> bodyMethod;
    private final Pair<Annotations, String>[] annotationsContentPair;
    private final ArrayList<Pair<String, String>> newsStatement;


    public BodyGenerator(
            Pair<Annotations, String>[] annotationsContentPair,
            ArrayList<Pair<String, String>> newsStatement,
            ArrayList<Pair<Pair<String,String>,String>> bodyMethod) {

        this.annotationsContentPair = annotationsContentPair;
        this.newsStatement= newsStatement;
        this.bodyMethod=bodyMethod;
    }


    @Override
    public String generate() {

        return   Corrections.annotationCreator(annotationsContentPair)
                +Corrections.newsStatement(newsStatement)
                +Corrections.createBodyMethod(bodyMethod);
    }


}
