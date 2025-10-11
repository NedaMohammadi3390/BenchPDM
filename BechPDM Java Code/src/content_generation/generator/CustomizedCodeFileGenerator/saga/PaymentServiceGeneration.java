package content_generation.generator.CustomizedCodeFileGenerator.saga;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;

public class PaymentServiceGeneration extends Builder implements TemplateGenerator, IGenerate {
    private final TemplateLoader loader = new TemplateLoader();
    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;

    public PaymentServiceGeneration(String applicationName, String path, String folderTpl, String packageName, String strpkname) {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
    }

    public String generatePaymentServiceApplication() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "PaymentServiceApplication.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Application")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Application"+".java", javaTpl);

        return null;
    }
    public String getClassName() {
        return applicationName;
    }

    public String generateInvoiceStatus() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "InvoiceStatus.java.tpl");

        javaTpl = javaTpl.replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/InvoiceStatus.java", javaTpl);

        return null;
    }

    public String generateInvoiceAggregate() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "InvoiceAggregate.java.tpl");

        javaTpl = javaTpl.replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/InvoiceAggregate.java", javaTpl);

        return null;
    }


    ////////////////////////////////////
    @Override
    public String generate() throws IOException {
        generatePaymentServiceApplication();
        generateInvoiceStatus();
        generateInvoiceAggregate();
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