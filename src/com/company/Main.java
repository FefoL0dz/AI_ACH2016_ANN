package com.company;

import com.company.multiLayerPerceptron.ANN;
import com.company.multiLayerPerceptron.di.DependencyInjector;
import com.company.utils.math.Sigmoid;

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

        int inputLayerNeuronNumber = 2;
        int outputLayerNeuronNumber = 2;
        int hiddenLayerNeuronNumber = 3;

        double learningRate = 0.5;
        int epochNumber = 100;

        getInstance().run(inputLayerNeuronNumber, outputLayerNeuronNumber,
                hiddenLayerNeuronNumber, learningRate, epochNumber, functionTag);
    }

    public void run (int inputLayerNeuronNumber,
                         int outputLayerNeuronNumber,
                         int hiddenLayerNeuronNumber,
                         double learningRate,
                         int epochNumber,
                         String functionTag) {
            neuralNetwork = DependencyInjector
                    .createInstance(inputLayerNeuronNumber, outputLayerNeuronNumber, hiddenLayerNeuronNumber,
                    learningRate, epochNumber, functionTag).inject();

            neuralNetwork.start();
    }
}
