package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixToTxtFile {

    public MatrixToTxtFile(String path, String content) {
        saveToFile(path, content);
    }

    public void saveToFile(String path, String content) {
        File file = new File(path + "\\matrix.txt");
        try {
            file.createNewFile();
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


