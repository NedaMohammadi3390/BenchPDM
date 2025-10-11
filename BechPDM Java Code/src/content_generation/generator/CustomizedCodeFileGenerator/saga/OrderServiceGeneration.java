package content_generation.generator.CustomizedCodeFileGenerator.saga;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;

public class OrderServiceGeneration extends Builder implements TemplateGenerator, IGenerate {
    private final TemplateLoader loader = new TemplateLoader();
    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;

    public OrderServiceGeneration(String applicationName, String path, String folderTpl, String packageName, String strpkname) {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
    }

    public String getClassName() {
        return applicationName;
    }
    public String generateOrderServiceApplication() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderServiceApplication.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Application")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Application"+".java", javaTpl);

        return null;
    }


    public String generateCreateInvoiceCommand() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "CreateInvoiceCommand.java.tpl");

        javaTpl = javaTpl.replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/CreateInvoiceCommand.java", javaTpl);

        return null;
    }

    public String generateCreateOrderCommand() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "CreateOrderCommand.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", "Create"+applicationName+"Command")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+"Create"+applicationName+"Command"+".java", javaTpl);

        return null;
    }
//************************************************

    public String generateCreateShippingCommand() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "CreateShippingCommand.java.tpl");

        javaTpl = javaTpl.replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/CreateShippingCommand.java", javaTpl);

        return null;
    }
    //************************************************
    public String generateItemType() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "ItemType.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Type")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Type"+".java", javaTpl);

        return null;
    }
    //************************************************
    public String generateOrderAggregate() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderAggregate.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Aggregate")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Aggregate"+".java", javaTpl);

        return null;
    }
    //************************************************
    public String generateOrderCommandController() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderCommandController.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"CommandController")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"CommandController"+".java", javaTpl);

        return null;
    }
    //************************************************
    public String generateOrderCommandService() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderCommandService.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"CommandService")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"CommandService"+".java", javaTpl);

        return null;
    }
    //************************************************
    public String generateOrderCreatedEvent() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderCreatedEvent.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"CreatedEvent")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"CreatedEvent"+".java", javaTpl);

        return null;
    }
    //************************************************
    public String generateOrderCreateDTO() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderCreateDTO.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"CreateDTO")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"CreateDTO"+".java", javaTpl);

        return null;
    }
    //************************************************
    public String generateOrderManagementSaga() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderManagementSaga.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"ManagementSaga")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"ManagementSaga"+".java", javaTpl);

        return null;
    }
    //************************************************
    public String generateOrderStatus() throws IOException {

        String javaTpl = loader.load(folderTpl,
                "OrderStatus.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Status")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Status"+".java", javaTpl);

        return null;
    }
    //************************************************
    @Override
    public String generate() throws IOException {
        generateOrderServiceApplication();
        generateCreateInvoiceCommand();
        generateCreateOrderCommand();
        generateCreateShippingCommand();
        generateItemType();
        generateOrderAggregate();
        generateOrderCommandController();
        generateOrderCommandService();
        generateOrderCreatedEvent();
        generateOrderCreateDTO();
        generateOrderManagementSaga();
        generateOrderStatus();
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
