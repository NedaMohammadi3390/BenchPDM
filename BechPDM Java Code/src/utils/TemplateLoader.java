package utils;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class TemplateLoader {
    private final String basePath = "src/templates/";

    public String load(String folder, String templateName) throws IOException {
        String path = basePath + folder + "/" + templateName;
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
