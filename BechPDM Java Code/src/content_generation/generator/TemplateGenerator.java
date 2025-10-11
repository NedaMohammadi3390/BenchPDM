package content_generation.generator;


import java.io.IOException;


public interface TemplateGenerator {


      String generate() throws IOException;


     void writeFile(String path, String content) throws IOException ;

}
