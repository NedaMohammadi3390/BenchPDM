package content_generation.generator;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.IGenerate;
import javafx.util.Pair;
import utils.Corrections;

import java.util.ArrayList;

public class MethodGenerator extends Builder implements IGenerate {
    private final AccessLevel accessLevel;
    private final boolean isStatic;
    private final boolean isFinal;
    private final Pair returnTypeName;
    private final Pair[] params;
    private final Exceptions exception;
    private final Pair<Annotations, String>[] annotationsContentPair;
    private final String returnStatement;
    private final  ArrayList<Pair<Pair<String,String>,String>> bodyMethod;

    public MethodGenerator(AccessLevel accessLevel,
                           boolean isStatic,
                           boolean isFinal,
                           Pair returnTypeName,
                           Pair[] params,
                           Exceptions exception,
                           Pair<Annotations, String>[] annotationsContentPair,
                           String returnStatement,
                           ArrayList<Pair<Pair<String,String>,String>> bodyMethod) {
        this.accessLevel = accessLevel;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.returnTypeName = returnTypeName;
        this.params = params;
        this.exception = exception;
        this.annotationsContentPair = annotationsContentPair;
        this.returnStatement = returnStatement;
        this.bodyMethod=bodyMethod;
    }

    @Override
    public String generate() {
        String parameter = Corrections.createParameters(params);
        String annotation = Corrections.annotationCreator(annotationsContentPair);

        return annotation
//                + Corrections.tabCalculator(tabCounter++)
                + (accessLevel == AccessLevel.PACKAGE_PRIVATE ? "" : accessLevel)
                + (isStatic ? "static" + space : "") + (isFinal ? "final" + space : "")
                + returnTypeName.getKey().toString()
                + returnTypeName.getValue().toString()
                + openParenthesis
                + parameter
                + closeParenthesis
                + (exception == null ? "" : exception.toString())
                + (openBrace + content() + Corrections.createBodyMethod(bodyMethod)+ (returnTypeName.getKey() != ReturnType.VOID ? ("return " + returnStatement + semicolon) : "")// الان محتوای متد چی میتونه باشه؟ content() دقیقا داره چیکار میکنه؟
                + closeBrace);
    }
}
