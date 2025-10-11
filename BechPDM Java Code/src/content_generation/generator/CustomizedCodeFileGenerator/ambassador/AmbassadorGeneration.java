package content_generation.generator.CustomizedCodeFileGenerator.ambassador;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;

import java.io.IOException;

public class AmbassadorGeneration extends Builder implements TemplateGenerator, IGenerate {
    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;

    GeneralTemplategenerator tmG;
    int idService;
    public AmbassadorGeneration(String applicationName,
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

    ////////////////////////////////////

    @Override
    public String generate() throws IOException {
        tmG.generate("AmbassadorProxyApplication.java", "Application");
        tmG.generate("ProxyController.java", "Controller");
        tmG.generate("ModuleConstants.java","ModuleConstants",5);
        tmG.generate("WebClientConfig.java","WebClientConfig",idService,1);
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
    }
}
