# BenchPDM

<div align="center">


**BenchPDM : Benchmarking Pattern Detection Methods 
in Microservice-Based Systems Using Automatically Generated Pattern-Assisted Testbeds**
</div>

## Table of Contents
- [Application of BenchPDM](#Application_of_BenchPDM)
   - [PBMC Module](#PBMC_Module)
   - [PDM-ES Module](#PDM-ES_Module)
- [Installation of the PDM-ES Module](#Installation_of_the_PBMC_Module)
- [Steps for Implementing PBMC](#Steps_for_Implementing_PBMC)
- [Installation of the PDM-ES Module](#Installation_of_the_PDM-ES_Module)
- [Steps for Implementing PDM-ES](#Steps_for_Implementing_PDM-ES)
- [Formal Description of Microservice Patterns within BenchPDM](#Formal_Description_of_Microservice_Patterns_within_BenchPDM)
- [Sample Microservice Patterns Generated by BenchPDM](#Sample_Microservice_Patterns_Generated_by_BenchPDM)

  ## Application of BenchPDM
  **BenchPDM**  is a benchmark for pattern-assisted microservice-based system (PAMBS) consisting of two important modules **PBMC** and **PDM-ES**.

  
  ### PBMC Module
  
  Pattern-based microservice systems creator **(PBMC)** module, is capable of generating microservice-based systems that communicate with each other through various microservice patterns, each serving a different purpose.


  ### PDM-ES Module
  
  The PDM Evaluation System **(PDM-ES)** module, is responsible for evaluating pattern detection methods based on common evaluation metrics in the field of design pattern detection. This part allows for the evaluation and comparison of different PDMs at different levels of complexity.
  

## Installation_of_the_PBMC_Module
+  ِYou need to specify the JavaFX module path when running the application. Use the following command (replacing paths and modules as necessary):

  ```bash
    java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar PBMC.jar
  ```

+ Ensure you replace /path/to/javafx-sdk/lib with the correct path to the JavaFX SDK on your machine.
