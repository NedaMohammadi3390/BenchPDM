package main;



import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class TracingFileGenerator {

    FileWriter writertxt;
    public TracingFileGenerator(String defaultPath, String name) throws IOException {

        String txtFile=defaultPath + name+".txt";

       writertxt = new FileWriter(txtFile);


    }



    public void tracesSaving(StringBuilder row) throws IOException {
        // Define the file path
        LocalTime myObj = LocalTime.now(); // Create a date object

        writertxt.write(row.toString());
        writertxt.write("\n");

        System.out.println("last time is:"+myObj);


        long lastTime = System.currentTimeMillis();
        System.out.println("LastTime milisecondes:"+lastTime);



    }
    public void closeFile() throws IOException {

        writertxt.flush();
        writertxt.close();
    }
}

