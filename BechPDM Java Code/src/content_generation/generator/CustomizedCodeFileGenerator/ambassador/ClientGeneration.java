package content_generation.generator.CustomizedCodeFileGenerator.ambassador;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.IOException;
import java.util.Map;

public class ClientGeneration extends Builder implements TemplateGenerator, IGenerate {
    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    int idService;

    GeneralTemplategenerator tmG;

    public ClientGeneration(String applicationName,
                                String path,
                                String folderTpl,
                                String packageName,
                                String strpkname,
                            int idService)
    {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
        this.idService = idService;
        tmG = new GeneralTemplategenerator(applicationName, path, folderTpl, packageName, strpkname);
    }

    public String getClassName() {
        return applicationName;
    }



    @Override
    public String generate() throws IOException {
        tmG.generate("ClientServiceApplication.java", "Application");
        tmG.generate("ClientController.java", "Controller");
        tmG.generate("ModuleConstants.java", "ModuleConstants",5);
        tmG.generate("WebClientConfig.java", "Config",idService,1);
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
    }
}