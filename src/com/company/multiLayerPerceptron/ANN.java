package com.company.multiLayerPerceptron;

import com.company.tools.IO.log.Logger;
import com.company.tools.generator.IRandomGenerator;
import com.company.tools.generator.IntGenerator;
import com.company.tools.graph.Plotter;
import com.company.tools.math.IFunction;
import com.company.utils.doubleConverter.DoubleConverter;
import com.company.utils.exception.ExitStatus;
import com.company.utils.exception.GlobalExceptionHandler;

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

    private Double inputDataSet[][];
    private Double outputDataSet[][];

    private Double inputXVector[];
    private Double expectedYvector[];
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
               Double[][] inputXVectors,
               Double[][] expectedYVectors,
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

        this.inputDataSet = inputXVectors;
        this.outputDataSet = expectedYVectors;

        this.inputXVector = inputXVectors[0];
        this.expectedYvector = expectedYVectors[0];

        this.hiddenWeightMatrix = hiddenWeightMatrix;
        this.outputWeightMatrix = outputWeightMatrix;

        this.hiddenZVector = DoubleConverter.toDouble(new double[hiddenLayerNeuronNumber]);
        this.obtainedYVector = DoubleConverter.toDouble(new double[outputLayerNeuronNumber]);

        this.outputErrorInformation = DoubleConverter.toDouble(new double[outputLayerNeuronNumber]);
        this.hiddenErrorInformation = DoubleConverter.toDouble(new double[hiddenLayerNeuronNumber - 1]); // not considering bias

        this.outputCorrectionTerm = DoubleConverter.toDouble(new double[outputWeightMatrix.length][outputWeightMatrix[0].length]);
        this.hiddenCorrectionTerm = DoubleConverter.toDouble(new double[hiddenWeightMatrix.length][hiddenWeightMatrix[0].length]);
        setBias();
    }

    private void setBias() {
        this.inputXVector[0] = bias;
        this.hiddenZVector[0] = bias;
    }

    public void updateCurrentEpoch() {
        this.currentEpoch++;
    }

    public void train() {
        int executionFinishStatus = ExitStatus.FINISHED_SUCCESSFULLY;
        try {
            startTraining();
        } catch(Exception e) {
            executionFinishStatus = ExitStatus.FINISHED_WITH_ERROR;
            GlobalExceptionHandler.handle(this, e);
        } finally {
            finishExecution();
            System.exit(executionFinishStatus);
        }
    }

    public void predict(double[] input) {
    }

    public void startTraining() {
        while (!isTerminated()) {
            feedForward();
            backPropagation();
            updateWeightMatrices();
            updateCurrentEpoch();
            changeTrainingData();
            //logIteration();
        }
    }

    private void changeTrainingData() {
        int dataSetInstancesNumber = inputDataSet.length;
        int randomIndex = new IntGenerator().generate(dataSetInstancesNumber);
        setNewTrainingInstance(Math.abs(randomIndex));
    }

    private void setNewTrainingInstance(int index) {
        this.inputXVector = inputDataSet[index];
        this.expectedYvector = outputDataSet[index];
    }

    private void updateWeightMatrices() {
        for (int i = 0; i < outputWeightMatrix.length; i++) {
            for (int j = 0; j < outputWeightMatrix[i].length; j++) {
                outputWeightMatrix[i][j] += outputCorrectionTerm[i][j];
            }
        }

        for (int i = 0; i < hiddenWeightMatrix.length; i++) {
            for (int j = 0; j < hiddenWeightMatrix[i].length; j++) {
                hiddenWeightMatrix[i][j] += hiddenCorrectionTerm[i][j];
            }
        }
    }

    private void backPropagation() {
        calculateOutputErrorInformation();
        calculateOutputCorrectionTerms();
        calculateHiddenErrorInformation();
        calculateHiddenCorrectionTerms();
    }

    private void calculateHiddenCorrectionTerms() {
        Double[] reducedAdjustRate = DoubleConverter.toDouble(new double[hiddenErrorInformation.length]);
        for (int i = 0; i < reducedAdjustRate.length; i++) {
            reducedAdjustRate[i] = learningRate * hiddenErrorInformation[i];
        }

        for (int i = 0; i < hiddenCorrectionTerm.length; i++) {
            for (int j = 1; j < hiddenCorrectionTerm[i].length; j++) {
                hiddenCorrectionTerm[i][j] = inputXVector[i] * reducedAdjustRate[j - 1];
            }
        }
    }

    //Here we mustn't  consider bias, we use this offset to perform this step without consider error in bias
    private void calculateHiddenErrorInformation() {
        for (int i = 0; i < hiddenLayerNeuronNumber - 1; i++) {
            hiddenErrorInformation[i] = (getReformattedWeights(i + 1)) * activationFunction.derivative(sumInputLayer(i + 1));
        }
    }

    private Double getReformattedWeights(int index) {
        Double result = 0.0;
        for (int i = 0; i < outputLayerNeuronNumber; i++) {
            result += outputWeightMatrix[index][i] * outputErrorInformation[i];
        }
        return result;
    }

    private void calculateOutputCorrectionTerms() {
        Double[] reducedAdjustRate = DoubleConverter.toDouble(new double[outputErrorInformation.length]);
        for (int i = 0; i < reducedAdjustRate.length; i++) {
            reducedAdjustRate[i] = learningRate * outputErrorInformation[i];
        }
        for (int i = 0; i < outputCorrectionTerm.length; i++) {
            for (int j = 0; j < outputCorrectionTerm[i].length; j++) {
                outputCorrectionTerm[i][j] = reducedAdjustRate[j] * hiddenZVector[i];
            }
        }
    }

    private void calculateOutputErrorInformation() {
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
        //throw new NotYetImplementedException();
        return false;
    }

    private void logIteration() {
        Logger.getInstance().logNeuralNetworkInfo(this);
        Plotter.getInstance().plot(this);
    }

    private void finishExecution() {
        //throw new NotYetImplementedException();
    }
}
