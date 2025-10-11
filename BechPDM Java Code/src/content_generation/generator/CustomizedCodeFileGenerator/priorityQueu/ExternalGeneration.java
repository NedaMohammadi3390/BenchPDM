package content_generation.generator.CustomizedCodeFileGenerator.priorityQueu;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;

public  class ExternalGeneration extends Builder implements TemplateGenerator, IGenerate {
    private final TemplateLoader loader = new TemplateLoader();

    String ExternalName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    public ExternalGeneration(String externalName, String path, String folderTpl, String packageName,String strpkname) {
        ExternalName = externalName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName=packageName;
        this.strpkname = strpkname;
    }

    @Override
    public String generate() throws IOException {
        String javaTpl = loader.load(folderTpl,
                "ExternalApplication.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE_NAME}", ExternalName+"Application")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+ExternalName + packageName+ "/ExternalApplication.java", javaTpl);
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
