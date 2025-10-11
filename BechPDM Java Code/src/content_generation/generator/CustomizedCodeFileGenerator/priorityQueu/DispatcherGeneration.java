package content_generation.generator.CustomizedCodeFileGenerator.priorityQueu;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public  class DispatcherGeneration extends Builder implements TemplateGenerator, IGenerate {
    StringBuilder sb = new StringBuilder();
    private final TemplateLoader loader = new TemplateLoader();

    String applicationName;
    String ListenerName;
    String path;
    String folderTpl;
    List<Integer> ports;
    String packageName;
    String strpkname;

    public DispatcherGeneration( String applicationName, String listenerName, String path, String folderTpl, List<Integer> ports,
                                 String packageName,String  strpkname) {

        this.applicationName = applicationName;
        ListenerName = listenerName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.ports = ports;
        this.packageName=packageName;
        this.strpkname = strpkname;
    }

    ////////////////////////////////////
    public String generateDispatcherApplication() throws IOException {
        String javaTpl = loader.load(folderTpl,
                "DispatcherApplication.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE_NAME}", applicationName+"Application")
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName +  packageName+"/DispatcherApplication.java", javaTpl);

        return null;
    }


    ////////////////////////////////////
    public String generateDispatcherListener() throws IOException {
        String after = "/external/process?data=\" + data, null, String.class);";
        String before = "restTemplate.postForObject(\"http://localhost:";



        for (int port : ports) {
            String url = before + port + after;


            sb.append(url).append("\n\t\t");
        }



        String javaTpl = loader.load(folderTpl,
                "DispatcherListener.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE_NAME}", ListenerName)
                .replace("${TEXT}",sb.toString())
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName+ packageName + "/DispatcherListener.java", javaTpl);

        return null;
    }


    @Override
    public String generate() throws IOException {
        generateDispatcherApplication();
        generateDispatcherListener();
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