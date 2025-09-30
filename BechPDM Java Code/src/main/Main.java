package main;

import data_structure.Microservice;
import file_generation.GraphGenerator;
import file_generation.ProjectFileGenerator;
import graphics.codes.Display;
import graphics.codes.VisualMicroservice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import strategy.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static String path2;
    public static StringBuilder strtrace= new StringBuilder();
    public static StringBuilder strUsagMemo= new StringBuilder();
    public static StringBuilder strCPUUsage= new StringBuilder();
    public static void main(String[] args) {
        launch(args);
    }

    public void startGenerator(List<Microservice> customPattern,
                               List<Pair<Pair<Microservice.Pattern, Integer>, Strategy>> userSelectedPatterns,
                               String path,
                               Stage stage ) throws IOException {
        //stage.setScene(null);
       this.path2=path;
        ProjectFileGenerator.defaultPath = path;

        ArrayList<Microservice> microserviceArrayList = new ArrayList<>();
        microserviceArrayList.addAll(customPattern);

        matrixFiller(microserviceArrayList, userSelectedPatterns);

        String log = logMatrix(microserviceArrayList);

        //code output
        PatternGenerator patternGenerator = new PatternGenerator();
        patternGenerator.generate(microserviceArrayList);

        //txt output
        new MatrixToTxtFile(path, log);

        //xml output
        GraphGenerator graphGenerator = new GraphGenerator(path, microserviceArrayList);
        graphGenerator.create();



        //csv output
        CSVGenerator csvGenerator = new CSVGenerator(path);
        csvGenerator.saveCSV(microserviceArrayList);

        TracingFileGenerator tracingGenerator = new TracingFileGenerator(path,"\\TracingFil50e");
        tracingGenerator.tracesSaving(strtrace);
        tracingGenerator.closeFile();

        TracingFileGenerator usageMemoryGenerator = new TracingFileGenerator(path,"\\UsageMemoryFile");
        usageMemoryGenerator.tracesSaving(strUsagMemo);
        usageMemoryGenerator.closeFile();

        TracingFileGenerator usageCPUGenerator = new TracingFileGenerator(path,"\\UsageCPUFile");
        usageCPUGenerator.tracesSaving(strCPUUsage);
        usageCPUGenerator.closeFile();
        System.out.println("writing in files is finished!!");



        //System.out.println(strtrace);
        //visual
//        ArrayList<VisualMicroservice> arrayList = GUI(microserviceArrayList);
//        Display display = new Display();
//        Stage stage2 =new Stage();
//        stage2.setTitle("Microservice dependency Graph (MDP)");
//        stage2.setMaximized(true);
//        try {
//            display.start(stage2, arrayList, path);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private void matrixFiller(
            ArrayList<Microservice> microserviceArrayList,
            List<Pair<Pair<Microservice.Pattern, Integer>, Strategy>> userSelectedPatterns)
    {
        for (Pair<Pair<Microservice.Pattern, Integer>, Strategy> userInput : userSelectedPatterns) {
            int times = userInput.getKey().getValue();
            for (int i = 0; i < times; i++) {

                Strategy strategy = userInput.getValue();
                microserviceArrayList.addAll(strategy.matrixFiller());

            }
        }
    } // End of matrixFiller
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String logMatrix(ArrayList<Microservice> matrices) {
        StringBuilder sb = new StringBuilder();
        for (Microservice microservice : matrices) {
            sb.append(microservice).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("resources/UI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Pattern-Assisted Microservice-based Systems (PAMBS)");
        primaryStage.setScene(scene);
        primaryStage.show();
        Runtime rt = Runtime.getRuntime();

        long total_mem = rt.totalMemory();

        long free_mem = rt.freeMemory();

        long used_mem = total_mem - free_mem;
        System.out.println("Amount of used memory: " + used_mem);
    }

    private ArrayList<VisualMicroservice> GUI(ArrayList<Microservice> microserviceArrayList) {
        ArrayList<VisualMicroservice> gui = new ArrayList<>();
        for (Microservice microservice : microserviceArrayList) {
            VisualMicroservice visualMicroservice = new VisualMicroservice(
                    microservice.getInfo(), //the name of microservice
                    microservice.getURI(), //the address of microservice
                    microservice.getConnectionsPair(), //the connections which microservice makes with others
                    microservice.getConnections() //the address of each microservice wich are connected to it
            );
            gui.add(visualMicroservice);
        }
        return gui;
    }
}
