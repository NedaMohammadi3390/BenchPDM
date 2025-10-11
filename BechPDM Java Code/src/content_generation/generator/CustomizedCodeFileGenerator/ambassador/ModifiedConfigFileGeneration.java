package content_generation.generator.CustomizedCodeFileGenerator.ambassador;

import content_generation.Builder;
import content_generation.IGenerate;
import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ModifiedConfigFileGeneration extends Builder implements IGenerate {
    private final StringBuilder sb =new StringBuilder();
    private final TemplateLoader loader = new TemplateLoader();
    String serviceName;
    String path;
    String folderTpl;
    int portService;
    Object clientinfo;
String before;
String after;
    List<Map<Map<String, Integer>,Integer>> ambassadorMap;

    public ModifiedConfigFileGeneration(String serviceName, String path, int port, String folderTpl){
        this.serviceName = serviceName;
        this.path = path;
        this.portService = port;
        this.folderTpl = folderTpl;
        this.ambassadorMap = new ArrayList<>();
    }

    public void setClientInfo(List<Map<Map<String, Integer>,Integer>> ambassadorMap) {
        this.ambassadorMap= ambassadorMap;
    }


    @Override
    public String generate() throws IOException {
        //    proxy.base-url = http://${AmbassadorSERVICE_NAME}:${AmbassadorSERVICE_PORT}
        before = "proxy.base-url";

        Set<String> lines = new HashSet<>();

        ambassadorMap.forEach(map ->
                map.forEach((innerMap, ambassadorId) -> {
                    innerMap.forEach((ambassadorName, ambassadorPort) -> {
                        String line = before + ambassadorId + " = http://" + ambassadorName + ":" + ambassadorPort;
                        lines.add(line);
                    });
                })
        );

        lines.forEach(line -> sb.append(line).append("\n"));


//**//
        String propTpl = loader.load(folderTpl,
                "application.properties.tpl");

        propTpl = propTpl.replace("${SERVICE_NAME}", serviceName)
                .replace("${SERVICE_PORT}", ""+portService)
        .replace("@{TEXT}",sb);

        writeFile(path+"/"+serviceName + "/src/main/resources/application.properties", propTpl);
        return "";
    }
    public String getClassName() {
        return serviceName;
    }

    private void writeFile(String path, String content) throws IOException {
//        new java.io.File(path).getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
    }


}