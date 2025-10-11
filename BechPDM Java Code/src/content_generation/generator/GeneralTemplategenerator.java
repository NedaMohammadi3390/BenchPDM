package content_generation.generator;

import utils.TemplateLoader;

import java.io.FileWriter;
import java.io.IOException;

public class GeneralTemplategenerator implements TemplateGenerator{
    private final TemplateLoader loader = new TemplateLoader();
    String applicationName;
    String path;
    String folderTpl;
    String packageName;
    String strpkname;
    String nameTPLFileJava;
    String postName;

    public GeneralTemplategenerator(String applicationName,
                                    String path,
                                    String folderTpl,
                                    String packageName,
                                    String strpkname) {
        this.applicationName = applicationName;
        this.path = path;
        this.folderTpl = folderTpl;
        this.packageName = packageName;
        this.strpkname = strpkname;
    }
    public String generate() throws IOException {
        return null;
    }

    public String generate(String nameTPLFileJava,String postName) throws IOException {
        String javaTpl = loader.load(folderTpl,
                nameTPLFileJava+".tpl");

        javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+postName)
                .replace("${PACKAGE}",strpkname);

        writeFile(path+"/"+applicationName + packageName+"/"+applicationName+postName+".java", javaTpl);

        return null;
    }
//***************************************************************
public String generate(String nameTPLFileJava,String postName,int id, int n) throws IOException {
    String javaTpl = loader.load(folderTpl,
            nameTPLFileJava+".tpl");

    javaTpl = javaTpl.replace("${SERVICE-NAME}", applicationName+postName)
            .replace("${PACKAGE}",strpkname);
//            .replace("${ID}",String.valueOf(id));

    writeFile(path+"/"+applicationName + packageName+"/"+applicationName+postName+".java", javaTpl);

    return null;
}
    //***************************************************************
public String generate(String nameTPLFileJava,String javaFile, int i) throws IOException {
    String javaTpl = loader.load(folderTpl,
            nameTPLFileJava+".tpl");

    javaTpl = javaTpl.replace("${SERVICE-NAME}", javaFile)
            .replace("${PACKAGE}",strpkname);

    writeFile(path+"/"+applicationName + packageName+"/"+javaFile+".java", javaTpl);

        return null;
}
    //***************************************************************
public String generate(String nameTPLFileJava) throws IOException {
    String javaTpl = loader.load(folderTpl,
            nameTPLFileJava+".tpl");

    javaTpl = javaTpl.replace("${PACKAGE}",strpkname);

    writeFile(path+"/"+applicationName + packageName+"/"+applicationName+postName+".java", javaTpl);

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



