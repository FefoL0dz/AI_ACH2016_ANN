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

    /**
     * Please configure learning preferences above
     */
    static final String functionTag = Sigmoid.TAG;
    static final double learningRate = 0.68;
    static final int epochNumber = 30000;
    static final int hiddenLayerSize = 9;

    public static Main getInstance() {
        return new Main();
    }

    public static void main(String[] args) {
        runXOR();
        runAND();
        runOR();
        runNOT();
        runCleanCharacters();
        runNonCleanCharacters();
    }

    private static void runXOR() {
        String fileDependency = "problemXOR.csv";
        String testFile = "problemXOR.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        run(mlp, testFile);
    }

    private static void runAND() {
        String fileDependency = "problemAND.csv";
        String testFile = "problemAND.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        run(mlp, testFile);
    }

    public static void runOR() {
        String fileDependency = "problemOR.csv";
        String testFile = "problemOR.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        run(mlp, testFile);
    }

    /*
    Eu implementei o NOT também pra fim de testes
     */
    private static void runNOT() {
        String fileDependency = "problemNOT.csv";
        String testFile = "problemNOT.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        run(mlp, testFile);
    }

    private static void runCleanCharacters() {
        String fileDependency = "caracteres-limpo.csv";
        String testFile = "caracteres-limpo.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        runWithCharactersOutput(mlp, testFile);
    }

    private static void runNonCleanCharacters() {
        /**
         * A configuração abaixo foi uma das melhoras configurações que nós encontramos
         * para resolver o problema dos caracteres com ruido professora.
         * TODO: Descomentar abaixo para testar
         */
//        String functionTag = Sigmoid.TAG;
//        double learningRate = 0.68;
//        int epochNumber = 30000;
//        int hiddenLayerSize = 9;

        String fileDependency = "caracteres-limpo.csv";
        String testFile = "caracteres-ruido.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        runWithCharactersOutput(mlp, testFile);
    }

    //Print binaries results
    private static void run(ANN mlp, final String testFile) {
        Double[][] input = DoubleConverter.doubleFromLists(new InputReader(testFile).readInput());
        Double[][] output = DoubleConverter.doubleFromLists(new InputReader(testFile).readOutput());
        for (int i = 0; i < input.length; i++) {
            Double[] result = mlp.predict(input[i]);
            Printer.print("Wanted result: ");
            Printer.printOutput(output[i % output.length], 0.5);
            Printer.print("Obtained result:");
            Printer.printOutput(result, 0.5);
            Printer.println("--------------------------------------------------");
        }
    }

    //Print characters result
    private static void runWithCharactersOutput(ANN mlp, final String testFile) {
        Double[][] input = DoubleConverter.doubleFromLists(new InputReader(testFile).readInput());
        Double[][] output = DoubleConverter.doubleFromLists(new InputReader(testFile).readOutput());
        for (int i = 0; i < input.length; i++) {
            Double[] result = mlp.predict(input[i]);
            Printer.print("Wanted result: ");
            Printer.printCharOutput(output[i % output.length]);
            Printer.print("Obtained result:");
            Printer.printCharOutput(result);
            Printer.println("--------------------------------------------------");
        }
    }

    //Create neuralNetwork instance and train with these settings
    public ANN train (double learningRate, int epochNumber, String functionTag, int hiddenLayerSize, String fileName) {
        neuralNetwork = DependencyInjector
                .createInstanceFromFileDependency(learningRate, epochNumber, functionTag, hiddenLayerSize, fileName).inject();
        neuralNetwork.train();
        return neuralNetwork;
    }

    //test validation method
    public ANN trainWithHardCodedDataSet(double learningRate, int epochNumber, String functionTag, int hiddenLayerSize) {
        double[][] inputs = {{1,-1,-1},{1,-1,1},{1,1,-1},{1,1,1}};
        double[][] outputs = {{0},{0},{0},{1}};
        neuralNetwork = DependencyInjector
                .createInstanceFromLocalTest(learningRate, epochNumber, functionTag, hiddenLayerSize, inputs, outputs).inject();
        neuralNetwork.train();
        return neuralNetwork;
    }
}
