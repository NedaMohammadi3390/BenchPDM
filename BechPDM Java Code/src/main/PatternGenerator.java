package main;

import content_generation.Builder;
import data_structure.Microservice;
import file_generation.ProjectFileGenerator;
import strategy.Strategy;

import java.util.ArrayList;

public class PatternGenerator {
    public void generate(ArrayList<Microservice> matrices) {
        for (Microservice microservice : matrices) {
            Strategy strategy = microservice.getStrategy();
            Builder[] builder = strategy.fileFiller(
                    microservice,
                    microservice.getRoleInPattern(),
                    microservice.getMicroserviceName(),
                    microservice.getConnections(),

                    microservice.getConnectionTypes()

            );
            for (Builder b : builder) {
                new ProjectFileGenerator(
                        microservice.getMicroserviceName(),
                        b
                );
            }
        }
    }
}
