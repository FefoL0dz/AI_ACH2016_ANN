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
        //TODO: Create a config file to centralize settings/preferences resources

        String functionTag = Sigmoid.TAG;
        double learningRate = 0.5;
        int epochNumber = 100;
        String fileDependency = "problemAND.csv";

//        int inputLayerNeuronNumber = 3;
//        int outputLayerNeuronNumber = 2;
//        int hiddenLayerNeuronNumber = 4;
//
//        double[] xInput = {1, 1, 1}; //TODO: Solve this problem - needs to pass bias as parameter
//        double[] yExpected = {1, 0};

        getInstance().run(learningRate, epochNumber, functionTag, fileDependency);
    }

    public void run (double learningRate, int epochNumber, String functionTag, String fileName) {
        neuralNetwork = DependencyInjector
                .createInstance(learningRate, epochNumber, functionTag, fileName).inject();

        neuralNetwork.start();
    }
}
