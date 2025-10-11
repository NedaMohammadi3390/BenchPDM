package content_generation;

import content_generation.generator.TemplateGenerator;

import java.io.IOException;

public abstract class PriorityQueueCodeFileGenerator extends Builder implements TemplateGenerator, IGenerate{
    @Override
    public abstract String generate() throws IOException;

    @Override
    public abstract void writeFile(String path, String content) throws IOException ;
}

