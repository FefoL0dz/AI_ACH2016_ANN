package com.company;

import com.company.multiLayerPerceptron.ANN;
import com.company.multiLayerPerceptron.di.DependencyInjector;
import com.company.tools.math.Sigmoid;

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
        double learningRate = 0.66;
        int epochNumber = 30000;
        int hiddenLayerSize = 3;
        String fileDependency = "problemAND.csv";

        ANN mlp = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
        runTests(mlp);
        ANN mlp1 = getInstance().train(learningRate, epochNumber, functionTag, hiddenLayerSize);
        runTests(mlp1);
    }

    private static void runTests(ANN mlp) {
        Double[] test1 = {1.0, -1.0, -1.0};
        Double[] test2 = {1.0, 1.0, -1.0};
        Double[] test3 = {1.0, -1.0, 1.0};
        Double[] test4 = {1.0, 1.0, 1.0};
        System.out.println(mlp.predict(test1));
        System.out.println(mlp.predict(test2));
        System.out.println(mlp.predict(test3));
        System.out.println(mlp.predict(test4));
        System.out.println("---------------------------------------------------------------------------");
    }

    public ANN train (double learningRate, int epochNumber, String functionTag, int hiddenLayerSize, String fileName) {
        neuralNetwork = DependencyInjector
                .createInstanceFromFileDependency(learningRate, epochNumber, functionTag, hiddenLayerSize, fileName).inject();
        neuralNetwork.train();
        return neuralNetwork;
    }

    public ANN train(double learningRate, int epochNumber, String functionTag, int hiddenLayerSize) {
        double[][] inputs = {{1,-1,-1},{1,-1,1},{1,1,-1},{1,1,1}};
        double[][] outputs = {{0},{0},{0},{1}};
        neuralNetwork = DependencyInjector
                .createInstanceFromLocalTest(learningRate, epochNumber, functionTag, hiddenLayerSize, inputs, outputs).inject();
        neuralNetwork.train();
        return neuralNetwork;
    }
}
