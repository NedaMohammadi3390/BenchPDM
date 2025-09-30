package file_generation;

import content_generation.Builder;

public class ProjectFileGenerator {
    public static String defaultPath;

    public ProjectFileGenerator(String microserviceName, Builder builder) {
        PackageFileGenerator packageGenerator = new PackageFileGenerator(defaultPath);
        String localPath = packageGenerator.createPackage(microserviceName, builder.getPackageName());
        ClassFileCreator classGenerator = new ClassFileCreator();
        classGenerator.createClassFile(localPath, builder.getPackageName(), builder.getClassName(), builder);
    }
}
