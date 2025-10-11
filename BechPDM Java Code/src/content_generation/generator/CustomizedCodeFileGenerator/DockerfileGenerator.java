package content_generation.generator.CustomizedCodeFileGenerator;

import content_generation.Builder;
import content_generation.IGenerate;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;

public class DockerfileGenerator extends Builder implements IGenerate {
    private final TemplateLoader loader = new TemplateLoader();
    private final String serviceName;
    String path;
    String folderTpl;
    public DockerfileGenerator(String serviceName,String path,String folderTpl){
        this.serviceName = serviceName;
        this.path = path;
        this.folderTpl = folderTpl;
    }
    @Override
    public String generate() throws IOException {
        String dockerTpl = loader.load(folderTpl, "Dockerfile.tpl");
        writeFile(path+"/"+ serviceName + "/Dockerfile", dockerTpl);
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
