package com.company.multiLayerPerceptron;

import com.company.tools.math.IFunction;
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

    private Double[][] outputCorrectionTerm;
    private Double[][] hiddenCorrectionTerm;

    IFunction<Double, Double> activationFunction;

    private Double inputXVector[];
    private final Double expectedYvector[];
    private Double obtainedYVector[];
    private Double hiddenZVector[];

    private Double hiddenErrorInformation[];
    private Double outputErrorInformation[];

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
        this.inputLayerNeuronNumber = inputLayerNeuronNumber + 1;
        this.outputLayerNeuronNumber = outputLayerNeuronNumber;
        this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber + 1;
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
        this.obtainedYVector = DoubleConverter.toDouble(new double[outputLayerNeuronNumber]);

        this.outputErrorInformation = DoubleConverter.toDouble(new double[outputLayerNeuronNumber]);
        this.hiddenErrorInformation = DoubleConverter.toDouble(new double[hiddenLayerNeuronNumber]);

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
        calculateErrorInformation();
    }

    private void calculateErrorInformation() {
        for (int i = 0; i < outputLayerNeuronNumber; i++) {
            outputErrorInformation[i] = (expectedYvector[i] - obtainedYVector[i]) * activationFunction.derivative(sumHiddenLayer(i));
        }
    }

    //We use this offset, starting with i = 1, not considering hidden layer bias. It do not must to be updated.
    private void feedForward() {
        for (int i = 1; i < hiddenLayerNeuronNumber; i++) {
            hiddenZVector[i] = activationFunction.execute(sumInputLayer(i));
        }

        for (int i = 0; i < outputLayerNeuronNumber; i++) {
            obtainedYVector[i] = activationFunction.execute(sumHiddenLayer(i));
        }
    }

    private Double sumHiddenLayer(int index) {
        double result = 0;
        for (int i = 0; i < hiddenLayerNeuronNumber; i++) {
            result += (hiddenZVector[i] * outputWeightMatrix[i][index]);
        }
        return result;
    }

    private double sumInputLayer(int index) {
        double result = 0;
        for (int i = 0; i < inputLayerNeuronNumber; i++) {
            result += (inputXVector[i] * hiddenWeightMatrix[i][index]);
        }
        return result;
    }

    private boolean isTerminated() {
        return (currentEpoch >= epochMaxNumber) || isStagnant();
    }

    private boolean isStagnant() {
        //TODO: Must t be implemented
        return false;
    }
}
