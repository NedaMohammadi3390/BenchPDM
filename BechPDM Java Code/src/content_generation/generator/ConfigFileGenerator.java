package content_generation.generator;

import content_generation.Builder;
import content_generation.IGenerate;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConfigFileGenerator extends Builder implements IGenerate {
    private final TemplateLoader loader = new TemplateLoader();
    String serviceName;
    String path;
    String folderTpl;
    int portService;
    List<Object> storage = new ArrayList<>();

    public ConfigFileGenerator(String serviceName,String path, int port, String folderTpl){
        this.serviceName = serviceName;
        this.path = path;
        this.portService = port;
        this.folderTpl = folderTpl;
    }

    public void setStorage(Object data) {
        this.storage.add(data);
    }

    @Override
    public String generate() throws IOException {
        String propTpl = loader.load(folderTpl,
                "application.properties.tpl");

        propTpl = propTpl.replace("${SERVICE_NAME}", serviceName)
                .replace("${ROUTES}", "")
                .replace("${SERVER_PORT}", ""+portService);

        writeFile(path+"/"+serviceName + "/src/main/resources/application.properties", propTpl);
        return "";
    }
    public String getClassName() {
        return serviceName;
    }

    private void writeFile(String path, String content) throws IOException {
//        new java.io.File(path).getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
    }


}
