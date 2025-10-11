package content_generation.generator.CustomizedCodeFileGenerator.pipeFilter;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.IOException;
import java.util.Map;

public class FilterGeneration extends Builder implements TemplateGenerator, IGenerate {
    StringBuilder sb = new StringBuilder();
    private final TemplateLoader loader = new TemplateLoader();

    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    GeneralTemplategenerator tmG;

    public FilterGeneration(String applicationName,
                            String path, String folderTpl,
                            String packageName,String strpkname) {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName=packageName;
        this.strpkname = strpkname;
        tmG = new GeneralTemplategenerator(applicationName, path, folderTpl, packageName, strpkname);
    }
    public String getClassName() {
        return applicationName;
    }
    ////////////////////////////////////
    public String generateFilterController() throws IOException {

        String javaTpl = loader.load(folderTpl, "FilterController.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Controller")
                .replace("${PACKAGE}", strpkname)
                .replace("${SERVICEURI}",applicationName);

        tmG.writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Controller"+".java", javaTpl);

        return null;
    }



    @Override
    public String generate() throws IOException {
        tmG.generate("FilterApplication.java", "Application");
        tmG.generate("FilterRoute.java", "Route");
        generateFilterController();
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
    }
}
