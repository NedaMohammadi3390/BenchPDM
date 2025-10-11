package content_generation.generator;

import content_generation.Builder;
import content_generation.IGenerate;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;

public class EventSourcingYMLGenerator extends Builder implements TemplateGenerator, IGenerate {

    protected final TemplateLoader loader = new TemplateLoader();
    String serviceName;

    public EventSourcingYMLGenerator(String serviceName, String path, String folderTpl, int portService, String[] otherOptions) {
        this.serviceName = serviceName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.portService = portService;
        OtherOptions = otherOptions;
    }

    String path;
    String folderTpl;
    int portService;
    String[] OtherOptions;



    @Override
    public String generate() throws IOException {
        String propTpl = loader.load(folderTpl,
                "application.yml.tpl");

        propTpl = propTpl.replace("${SERVICE_NAME}", serviceName)
                .replace("${ROUTES}", "")
                .replace("${SERVER_PORT}", ""+portService);

        writeFile(path+"/"+serviceName + "/src/main/resources/application.yml", propTpl);
        return "";
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
        {
//        new java.io.File(path).getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(path)) {
                writer.write(content);
            }
        }
    }
}
