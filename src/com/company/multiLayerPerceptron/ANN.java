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
    private int epochMaxNumber;

    private int currentEpoch;

    private Double[][] hiddenWeightMatrix;
    private Double[][] outputWeightMatrix;

    Function<Double, Double> activationFunction;

    private Double inputXVector[];
    private Double expectedYvector[];

    public void start() {

    }

    public ANN(int inputLayerNeuronNumber,
               int outputLayerNeuronNumber,
               int hiddenLayerNeuronNumber,
               double learningRate,
               int epochMaxNumber,
               Function<Double, Double> activationFunction,
               Double[] inputXVector,
               Double[] expectedYvector,
               Double[][] hiddenWeightMatrix,
               Double[][] outputWeightMatrix) {
        this.inputLayerNeuronNumber = inputLayerNeuronNumber;
        this.outputLayerNeuronNumber = outputLayerNeuronNumber;
        this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber;
        this.learningRate = learningRate;
        this.errorRate = Double.POSITIVE_INFINITY;
        this.epochMaxNumber = epochMaxNumber;
        this.activationFunction = activationFunction;
        this.currentEpoch = 0;
        this.inputXVector = inputXVector;
        this.expectedYvector = expectedYvector;

        this.hiddenWeightMatrix = hiddenWeightMatrix;
        this.outputWeightMatrix = outputWeightMatrix;
    }

    public void updateCurrentEpoch() {
        this.currentEpoch++;
    }
}
