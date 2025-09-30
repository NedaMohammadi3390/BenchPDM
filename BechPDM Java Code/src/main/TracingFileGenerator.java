package main;



import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class TracingFileGenerator {


    File file;
    BufferedWriter writer;
    FileWriter writertxt;
    public TracingFileGenerator(String defaultPath, String name) throws IOException {

      // String csvFile=defaultPath + name+".xlsx";
        String txtFile=defaultPath + name+".txt";
       // file = new File(csvFile);
       writertxt = new FileWriter(txtFile);
       // file.createNewFile();

        //writer = new BufferedWriter(new FileWriter(file));

    }



    public void tracesSaving(StringBuilder row) throws IOException {
        // Define the file path
        LocalTime myObj = LocalTime.now(); // Create a date object
      //  writer.write(row.toString()); // Append the row data and move to the next line


        writertxt.write(row.toString());
        writertxt.write("\n");


       //12 System.out.println(row.toString());
        System.out.println("last time is:"+myObj);


        long lastTime = System.currentTimeMillis();
        System.out.println("LastTime milisecondes:"+lastTime);



    }
    public void closeFile() throws IOException {
      //  writer.flush();
      //  writer.close();

        writertxt.flush();
        writertxt.close();
    }
}

