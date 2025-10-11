// TODO automatic tab
// TODO Multiple classes in one file

package content_generation.generator;

import content_generation.Builder;
import content_generation.Constants;
import content_generation.IGenerate;
import javafx.util.Pair;
import utils.Corrections;
import java.util.Arrays;

public class ClassGenerator extends Builder implements IGenerate {
    private final AccessLevel accessLevel;
    private final ElementType elementType;
    private final String className;
    private final String[] implement;
    private final String extend;
    private final ConstructorGenerator[] constructorGenerators;
    private final Pair<Annotations, String>[] annotationsContentPair;
    private String importsContent;


    public ClassGenerator(AccessLevel accessLevel,
                          ElementType elementType,
                          String className,
                          String[] implement,
                          String extend,
                          ConstructorGenerator[] constructorGenerators,
                          Pair<Annotations, String>[] annotationsContentPair,
                          String[] imports) {
        this.accessLevel = accessLevel;
        this.className = className;
        this.implement = implement;
        this.extend = extend;
        this.constructorGenerators = constructorGenerators;
        this.annotationsContentPair = annotationsContentPair;
        this.importsContent = String.join("\n", imports);
        this.elementType = elementType;
    }

    @Override
    public String generate() {
        StringBuilder constructorsContent = new StringBuilder();

        if (constructorGenerators != null)
            Arrays.stream(constructorGenerators).forEach(constructorGenerator -> {
                constructorGenerator.setName(className);
                constructorsContent.append(constructorGenerator.generate());
            });

        String annotation = Corrections.classAnnotationCreator(annotationsContentPair);
        StringBuilder implementStatements = new StringBuilder(Keywords.IMPLEMENTS.toString()).append(space);
        if (implement != null) {
            for (String imp : implement) {
                implementStatements.append(imp).append(comma);
            }
            implementStatements.delete(implementStatements.lastIndexOf(","), implementStatements.length());
        }

        return "\n"+"\n"+importsContent+"\n"+"\n"+
                annotation
                + (accessLevel == Constants.AccessLevel.PACKAGE_PRIVATE ? "" : accessLevel.toString())
                + elementType
                + className
                + space
                + (extend.equals("") ? "" : (Keywords.EXTENDS.toString() + extend + space))
                + (implement == null ? "" : (implementStatements))
                + openBrace+"\n"
                + constructorsContent
                + content()
                + closeBrace;
    }

    public String getClassName() {
        return className;
    }
}