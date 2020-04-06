package com.company.multiLayerPerceptron;

import com.company.utils.math.Function;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class ANN {

    private int inputLayerNeuronNumber;
    private int outputLayerNeuronNumber;
    private int hiddenLayerNeuronNumber;

    private double learningRate;
    private double errorRate;
    private int epochNumber;

    Function<Double, Double> activationFunction;

    public ANN(int inputLayerNeuronNumber,
               int outputLayerNeuronNumber,
               int hiddenLayerNeuronNumber,
               double learningRate,
               double errorRate,
               int epochNumber,
               Function<Double, Double> activationFunction) {
        this.inputLayerNeuronNumber = inputLayerNeuronNumber;
        this.outputLayerNeuronNumber = outputLayerNeuronNumber;
        this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber;
        this.learningRate = learningRate;
        this.errorRate = errorRate;
        this.epochNumber = epochNumber;
        this.activationFunction = activationFunction;
    }
}
