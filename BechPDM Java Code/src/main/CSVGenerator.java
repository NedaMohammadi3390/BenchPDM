package main;

import data_structure.Microservice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVGenerator {
    private final String defaultPath;

    public CSVGenerator(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public void saveCSV(ArrayList<Microservice> matrices) {
        File file = new File(defaultPath + "\\matrix.csv");
        try {
            file.createNewFile();
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(matrices.get(0).getMatrixAttributes());

            for (Microservice microservice : matrices) {
                String line = csvLines(microservice);
                writer.write(line);
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String csvLines(Microservice microservice) {
        String comma = ",";
        return microservice.getId() + comma + microservice.getMicroserviceName()
                + comma + microservice.getURI() + comma + microservice.getConnectionType()
                + comma + microservice.isDuplicate() + comma + microservice.getCreationTime()
                + comma + microservice.getPatternName() + comma + microservice.getRoleInPattern()
                + comma + microservice.getConnectedMicroservices() + "\n";
    }


}
