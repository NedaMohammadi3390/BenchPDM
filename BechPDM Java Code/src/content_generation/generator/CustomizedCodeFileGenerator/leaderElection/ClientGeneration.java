package content_generation.generator.CustomizedCodeFileGenerator.leaderElection;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;
import java.io.IOException;

public class ClientGeneration extends Builder implements TemplateGenerator, IGenerate {
    StringBuilder sb = new StringBuilder();
    private final TemplateLoader loader = new TemplateLoader();

    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    int leaderPort;
    String leaderName;

    GeneralTemplategenerator tmG;
    public ClientGeneration(String applicationName,
                            String path, String folderTpl,
                            String packageName,String strpkname, int leaderPort, String leaderName) {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName=packageName;
        this.strpkname = strpkname;
        this.leaderName = leaderName;
        this.leaderPort = leaderPort;
        tmG = new GeneralTemplategenerator(applicationName, path, folderTpl, packageName, strpkname);
    }
    public String getClassName() {
        return applicationName;
    }
    ////////////////////////////////////
    public String generateClientController() throws IOException {
        String javaTpl = loader.load(folderTpl,
                "ClientController.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Controller")
                .replace("${PACKAGE}",strpkname)
                .replace("${LEADER-PORT}",String.valueOf(leaderPort))
                .replace("${LEADER-NAME}",leaderName);

        tmG.writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Controller"+".java", javaTpl);

        return null;
    }

    @Override
    public String generate() throws IOException {
        tmG.generate("ClientApplication.java", "Application");
        generateClientController();
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
    }
}
