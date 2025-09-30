package file_generation;

import content_generation.Builder;
import content_generation.generator.PackageSignatureGenerator;
import utils.Corrections;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassFileCreator {

    public void createClassFile(String localPath, String packagePath, String className, Builder builder) {
        if (!Corrections.isCorrectPackage(localPath)) return;
        if (Corrections.isInvalid(className)) return;

        File file = new File(localPath + "\\" + className + ".java");

        try {
            file.createNewFile();
            writeClassContent(file, packagePath, builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeClassContent(File file, String packageName, Builder builder) {
        PackageSignatureGenerator packageSignatureGenerator = new PackageSignatureGenerator(packageName);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(packageSignatureGenerator.generate());
            writer.write(builder.build());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
