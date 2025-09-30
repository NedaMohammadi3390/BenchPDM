package content_generation.generator;

import content_generation.Builder;
import content_generation.IGenerate;

import static content_generation.Constants.ElementType.PACKAGE;

public class PackageSignatureGenerator extends Builder implements IGenerate {
    private final String packageName;

    public PackageSignatureGenerator(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String generate() {
        return PACKAGE.toString()
                + packageName
                + semicolon;
    }

}
