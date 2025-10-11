package content_generation.generator.CustomizedCodeFileGenerator.priorityQueu;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;

public class ClientGeneration extends  Builder implements TemplateGenerator, IGenerate {
    StringBuilder sb = new StringBuilder();
    private final TemplateLoader loader = new TemplateLoader();

    String applicationName;
    String schedulerName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    int dispatchPort;
    public ClientGeneration(String applicationName, String schedulerName,
                            String path, String folderTpl,
                            String packageName,String strpkname,int dispatchPort) {
        this.applicationName = applicationName;
        this.schedulerName = schedulerName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName=packageName;
        this.strpkname = strpkname;
        this.dispatchPort = dispatchPort;
    }
    ////////////////////////////////////
    public String generateClientApplication() throws IOException {
        String javaTpl = loader.load(folderTpl,
                "ClientApplication.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE_NAME}", applicationName+"Application")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/ClientApplication.java", javaTpl);

        return null;
    }
    public String getClassName() {
        return applicationName;
    }
    ////////////////////////////////////

    public String generateClientController() throws IOException {
        String javaTpl = loader.load(folderTpl,
                "ClientController.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE_NAME}", applicationName)
                .replace("${PACKAGE}",strpkname)
                .replace("${DISPATCH}",String.valueOf(dispatchPort));

        writeFile(path+"/"+applicationName + packageName+"/ClientController.java", javaTpl);

        return null;
    }
    ///////////////////////////////////////////////////
    public String generateRabbitConfig() throws IOException {

        String rabbitTpl = loader.load(folderTpl,
                "RabbitConfig.java.tpl");

        rabbitTpl = rabbitTpl.replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/RabbitConfig.java", rabbitTpl);
        return null;
    }
    ////////////////////////////////////
    public String generateClientScheduler() throws IOException {

// تعداد taskها (1 تا 10)
        int size = ThreadLocalRandom.current().nextInt(1, 11);

// ایجاد لیست taskNumberها و shuffle کردن آن
        List<Integer> taskNumbers = new ArrayList<>();
        for (int n = 1; n <= 10; n++) {
            taskNumbers.add(n);
        }
        Collections.shuffle(taskNumbers);

// ایجاد لیست priorityها و shuffle کردن آن
        List<Integer> priorities = new ArrayList<>();
        for (int n = 1; n <= 10; n++) {
            priorities.add(n);
        }
        Collections.shuffle(priorities);

// استفاده از اولین 'size' عدد برای taskها و priorityها
        for (int i = 0; i < size; i++) {
            int taskNumber = taskNumbers.get(i);
            int priority = priorities.get(i);

            String taskName = "Task" + taskNumber;

            sb.append("sendTask(\"").append(taskName)
                    .append("\",").append(priority)
                    .append(");\n\t");
        }
        String javaTpl = loader.load(folderTpl,
                "ClientScheduler.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE_NAME}", schedulerName)
                .replace("${PACKAGE}",strpkname)
                .replace("${TEXT}",sb.toString());

        writeFile(path+"/"+applicationName + packageName+"/ClientScheduler.java", javaTpl);

        return null;
    }


    @Override
    public String generate() throws IOException {
        generateClientScheduler();
        generateClientApplication();
        generateRabbitConfig();
        generateClientController();
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
