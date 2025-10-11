package content_generation.generator.CustomizedCodeFileGenerator.leaderElection;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;
import java.io.IOException;

public class LeaderGeneration extends Builder implements TemplateGenerator, IGenerate {

    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;

    GeneralTemplategenerator tmG;

    public LeaderGeneration(String applicationName,
                            String path, String folderTpl,
                            String packageName, String strpkname) {
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
        tmG.generate("ScheduledTasksForLeaderElection.java", "ScheduledTasksFor"+applicationName,9);
        tmG.generate("SampleLeaderElectionWithSpringCloudApplication.java", applicationName+"WithSpringCloudApplication",9);
        tmG.generate("ManagerContextForLeaderElection.java", "ManagerContextFor"+applicationName,9);
        tmG.generate("LeaderController.java", applicationName+"Controller",9);
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
    }
}
