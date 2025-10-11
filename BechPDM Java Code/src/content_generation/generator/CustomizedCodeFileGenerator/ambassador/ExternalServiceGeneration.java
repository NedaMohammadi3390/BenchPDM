package content_generation.generator.CustomizedCodeFileGenerator.ambassador;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;
import java.io.IOException;

public class ExternalServiceGeneration extends Builder implements TemplateGenerator, IGenerate {

    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    GeneralTemplategenerator tmG;

    public ExternalServiceGeneration(String applicationName,
                                String path,
                                String folderTpl,
                                String packageName,
                                String strpkname)
    {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
        tmG = new GeneralTemplategenerator(applicationName, path, folderTpl, packageName, strpkname);
    }

    public String getClassName() {
        return applicationName;
    }



    @Override
    public String generate() throws IOException {
        tmG.generate("MockApiApplication.java", "Application");
        tmG.generate("ExternalApiController.java", "Controller");
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
    }
}