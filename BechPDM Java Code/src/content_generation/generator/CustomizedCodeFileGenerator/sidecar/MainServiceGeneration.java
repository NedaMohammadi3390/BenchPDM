package content_generation.generator.CustomizedCodeFileGenerator.sidecar;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;

import java.io.IOException;

public class MainServiceGeneration extends Builder implements TemplateGenerator, IGenerate {
    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    GeneralTemplategenerator tmG;

    public MainServiceGeneration(String applicationName, String path, String folderTpl, String packageName, String strpkname) {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
        tmG = new GeneralTemplategenerator(applicationName,path,folderTpl,packageName,strpkname);
    }


    public String getClassName() {
        return applicationName;
    }



    public String generate() throws IOException {
        tmG.generate("MainServiceApplication.java","Application");
        tmG.generate("MainServiceController.java","Controller");
        tmG.generate("SomeEntityClient.java","Client");
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {

    }


}
