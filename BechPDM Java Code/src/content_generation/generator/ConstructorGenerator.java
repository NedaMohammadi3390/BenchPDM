package content_generation.generator;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.IGenerate;
import javafx.util.Pair;
import utils.Corrections;

import java.util.ArrayList;

public class ConstructorGenerator extends Builder implements IGenerate {
    private final Pair<Annotations, String>[] annotationsContentPair;
    private AccessLevel accessLevel;
    private String name;
    private final Pair<Constants.Annotations,String>[] params;
    private String[] connections;
    private final ArrayList<Pair<String, String>> newsStatement;

    public ConstructorGenerator(AccessLevel accessLevel,
                                String name,
                                Pair<Annotations, String>[] annotationsContentPair,
                                Pair<Constants.Annotations,String>[] params,
                                String[] connections,
                                ArrayList<Pair<String, String>> newsStatement) {
        this.accessLevel = accessLevel;
        this.name = name;
        this.annotationsContentPair = annotationsContentPair;
        this.params = params;
        this.connections = connections;
        this.newsStatement = newsStatement;
    }

    @Override
    public String generate() {
        String parameter = Corrections.createParameters(params);
        String initiate = Corrections.initiate(params);
        String annotation = Corrections.annotationCreator(annotationsContentPair);
        String connection = Corrections.createConnections(connections);
        String newsStatement = Corrections.newsStatement(this.newsStatement);
        return annotation
                + (accessLevel == AccessLevel.PACKAGE_PRIVATE ? "" : accessLevel)
                + name
                + openParenthesis
                + parameter
                + closeParenthesis
                + openBrace
                + initiate
                + newsStatement
                + connection
                + closeBrace;
    }

    void setName(String name) {
        this.name = name;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setConnections(String[] connections) {
        this.connections = connections;
    }

}
