package content_generation.generator.CustomizedCodeFileGenerator.pipeFilter;

import content_generation.Builder;
import content_generation.IGenerate;
import content_generation.generator.GeneralTemplategenerator;
import content_generation.generator.TemplateGenerator;
import utils.TemplateLoader;

import java.io.IOException;
import java.util.Map;

public class ApiGatewayGeneration extends Builder implements TemplateGenerator, IGenerate {
    StringBuilder sb = new StringBuilder();
    private final TemplateLoader loader = new TemplateLoader();

    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    Map<String, Integer> microservices;

    GeneralTemplategenerator tmG;

    public ApiGatewayGeneration(String applicationName,
                                String path,
                                String folderTpl,
                                String packageName,
                                String strpkname,
                                Map<String, Integer> microservices)
    {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
        this.microservices = microservices;
        tmG = new GeneralTemplategenerator(applicationName, path, folderTpl, packageName, strpkname);
    }

    public String getClassName() {
        return applicationName;
    }

    ////////////////////////////////////
    public String generateApiGatewayRoute() throws IOException {

        String before = "from(\"direct:start\")\n" ;
        String middle ="        .to(\"http://";
        String after = "        .log(\"Final response: ${body}\");";

        sb.append(before);

        for (Map.Entry<String, Integer> entry : microservices.entrySet()) {
            String filterName = entry.getKey();
            Integer port = entry.getValue();

            String url = middle+ filterName + ":" + port + "/" + filterName;


            sb.append(url).append("\n");
        }
        sb.append(after).append("\n\n");


        String javaTpl = loader.load(folderTpl, "ApiGatewayRoute.java.tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+"Route")
                .replace("${TEXT}", sb.toString())
                .replace("${PACKAGE}", strpkname);

        tmG.writeFile(path+"/"+applicationName + packageName+"/"+applicationName+"Route"+".java", javaTpl);

        return null;
    }

    @Override
    public String generate() throws IOException {
        tmG.generate("ApiGatewayApplication.java", "Application");
        tmG.generate("ApiGatewayController.java", "Controller");
        generateApiGatewayRoute();
        return null;
    }

    @Override
    public void writeFile(String path, String content) throws IOException {
    }
}
