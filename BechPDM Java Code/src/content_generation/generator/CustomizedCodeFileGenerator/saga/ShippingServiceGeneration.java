package content_generation.generator.CustomizedCodeFileGenerator.saga;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;

public class ShippingServiceGeneration extends Builder implements TemplateGenerator, IGenerate {
    private final TemplateLoader loader = new TemplateLoader();
    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;

    public ShippingServiceGeneration(String applicationName, String path, String folderTpl, String packageName, String strpkname) {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
    }
    public String getClassName() {
        return applicationName;
    }
    //************************************************************
    public String generateShippingAggregate() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "ShippingAggregate.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Aggregate")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Aggregate"+".java", javaTpl);

        return null;
    }
//************************************************************

    public String generateShippingServiceApplication() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "ShippingServiceApplication.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"ServiceApplication")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"ServiceApplication"+".java", javaTpl);

        return null;
    }
    ////////////////////////////////////
    @Override
    public String generate() throws IOException {
        generateShippingAggregate();
        generateShippingServiceApplication();
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
//        new java.io.File(path).getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
    }
}