package com.company.multiLayerPerceptron;

import com.company.math.IFunction;
import com.company.utils.doubleConverter.DoubleConverter;

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

    IFunction<Double, Double> activationFunction;

    private Double inputXVector[];
    private Double expectedYvector[];
    private Double hiddenZVector[];

    private final double bias = 1;

    public ANN(int inputLayerNeuronNumber,
               int outputLayerNeuronNumber,
               int hiddenLayerNeuronNumber,
               double learningRate,
               int epochMaxNumber,
               IFunction<Double, Double> activationFunction,
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

        this.hiddenZVector = DoubleConverter.toDouble(new double[hiddenLayerNeuronNumber]);
        setBias();
    }

    private void setBias() {
        this.inputXVector[0] = bias;
        this.hiddenZVector[0] = bias;
    }

    public void updateCurrentEpoch() {
        this.currentEpoch++;
    }

    public void start() {
        while (!isTerminated()) {
            feedForward();
            backPropagation();
            updateWeightMatrixes();
            updateCurrentEpoch();
        }
    }

    private void updateWeightMatrixes() {
    }

    private void backPropagation() {
    }

    private void feedForward() {
    }

    private boolean isTerminated() {
        return (currentEpoch < epochMaxNumber) && isStagnant();
    }

    private boolean isStagnant() {
        //TODO: Must t be implemented
        return false;
    }
}
