package content_generation.generator;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.IGenerate;
import javafx.util.Pair;
import utils.Corrections;

public class VariableGenerator extends Builder implements IGenerate {
    private final AccessLevel accessLevel;
    private final boolean isStatic;
    private final boolean isFinal;
    private final Pair pair;
    private final String value;
    private final Pair[] annotationsContentPair;
    private final boolean hasGetter;
    private final boolean hasSetter;

    public VariableGenerator(AccessLevel accessLevel,
                             boolean isStatic,
                             boolean isFinal,
                             Pair pair,
                             String value,
                             Pair[] annotationsContentPair,
                             boolean hasGetter,
                             boolean hasSetter) {
        this.accessLevel = accessLevel;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.pair = pair;
        this.value = value;
        this.annotationsContentPair = annotationsContentPair;
        this.hasGetter = hasGetter;
        this.hasSetter = hasSetter;
    }


    @Override
    public String generate() {
        String varType = pair.getKey().toString();
        String varName = (String) pair.getValue();

        String annotation = Corrections.annotationCreator(annotationsContentPair);

        return annotation
//                + Corrections.tabCalculator(tabCounter)
                + (accessLevel == AccessLevel.PACKAGE_PRIVATE ? "" : accessLevel)
                + (isStatic ? "static" + space : "")
                + (isFinal ? "final" + space : "")
                + pair.getKey().toString()
                + pair.getValue()
                + (!value.equals("") ? equalSymbol + value : "")
                + semicolon
                + generateSetter(varName, varType)
                + generateGetter(varName, varType);
    }

    private String generateSetter(String varName, String varType) {
        if (!hasSetter) return "";
        return AccessLevel.PUBLIC
                + ""
                + ReturnType.VOID
                + "set"
                + varName.substring(0, 1).toUpperCase()
                + varName.substring(1)
                + openParenthesis
                + VariableType.AddNewType(varType)
                + varName
                + closeParenthesis
                + openBrace
                + Corrections.initiate(varName)
                + closeBrace;
    }

    private String generateGetter(String varName, String varType) {
        if (!hasGetter) return "";
        return AccessLevel.PUBLIC
                + ""
                + varType
                + "get"
                + varName.substring(0, 1).toUpperCase()
                + varName.substring(1)
                + openParenthesis
                + closeParenthesis
                + openBrace
                + Keywords.RETURN
                + varName
                + semicolon
                + closeBrace;

    }

}
