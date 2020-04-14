package com.company;

import com.company.multiLayerPerceptron.ANN;
import com.company.multiLayerPerceptron.di.DependencyInjector;
import com.company.tools.IO.Printer;
import com.company.tools.IO.input.InputReader;
import com.company.tools.math.Sigmoid;
import com.company.utils.doubleConverter.DoubleConverter;

/**
  Created by: Felipe Lodes in 07/04/2020.
  Discipline: ACH2016 - "Inteligência Artificial"
  Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class Main {

    ANN neuralNetwork;

    public static Main getInstance() {
        return new Main();
    }

    public static void main(String[] args) {

        String functionTag = Sigmoid.TAG;
        double learningRate = 0.68;
        int epochNumber = 10000;
        int hiddenLayerSize = 4;

        String fileDependency = "problemXOR.csv";
        String testFile = "problemXOR.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        run(mlp, testFile);
    }

    private static void run(ANN mlp, final String testFile) {
        Double[][] input = DoubleConverter.doubleFromLists(new InputReader(testFile).readInput());
        Double[][] output = DoubleConverter.doubleFromLists(new InputReader(testFile).readOutput());
        for (int i = 0; i < input.length; i++) {
            Double[] result = mlp.predict(input[i]);
            Printer.print("Wanted result: ");
            //Printer.printCharOutput(output[i % output.length]);
            Printer.printOutput(output[i % output.length], 0.5);
            Printer.print("Obtained result:");
            //Printer.printCharOutput(result);
            Printer.printOutput(result, 0.5);
            Printer.println("--------------------------------------------------");
        }
    }

    private static void runTests(ANN mlp, final String fileTest) {
        //double threshold = 0.5;
        Double[][] test = DoubleConverter.doubleFromLists(new InputReader(fileTest).readInput());
        for (int i = 0; i < test.length; i++) {
            Double[] output = mlp.predict(test[i]);
           // Printer.printVector(output);
            Printer.printCharOutput(output);
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    private static void runTests(ANN mlp) {
        //Boolean Table (1)
        Double[] test1 = {1.0, -1.0};
        Double[] test2 = {1.0, 1.0};

//        //Boolean Table (2)
//        Double[] test1 = {1.0, -1.0, -1.0};
//        Double[] test2 = {1.0, 1.0, -1.0};
//        Double[] test3 = {1.0, -1.0, 1.0};
//        Double[] test4 = {1.0, 1.0, 1.0};

        double threshold = 0.5;
        Printer.printOutput(mlp.predict(test1), threshold);
        Printer.printOutput(mlp.predict(test2), threshold);
       // Printer.printOutput(mlp.predict(test3), threshold);
       // Printer.printOutput(mlp.predict(test4), threshold);
        System.out.println("---------------------------------------------------------------------------");
    }

    public ANN train (double learningRate, int epochNumber, String functionTag, int hiddenLayerSize, String fileName) {
        neuralNetwork = DependencyInjector
                .createInstanceFromFileDependency(learningRate, epochNumber, functionTag, hiddenLayerSize, fileName).inject();
        neuralNetwork.train();
        return neuralNetwork;
    }

    public ANN trainWithHardCodedDataSet(double learningRate, int epochNumber, String functionTag, int hiddenLayerSize) {
        double[][] inputs = {{1,-1,-1},{1,-1,1},{1,1,-1},{1,1,1}};
        double[][] outputs = {{0},{0},{0},{1}};
        neuralNetwork = DependencyInjector
                .createInstanceFromLocalTest(learningRate, epochNumber, functionTag, hiddenLayerSize, inputs, outputs).inject();
        neuralNetwork.train();
        return neuralNetwork;
    }
}
