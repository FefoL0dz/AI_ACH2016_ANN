package com.company.multiLayerPerceptron;

import com.company.utils.generator.MatrixGenerator;
import com.company.utils.generator.VectorGenerator;
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
    //private double meanError;

    private Double[][] hiddenWeightMatrix;
    private Double[][] outputWeightMatrix;

    Function<Double, Double> activationFunction;

    private Double inputXVector[];
    private Double expectedYvector[];

    private final double bias = 1;


    public void start() {

    }

    public ANN(int inputLayerNeuronNumber,
               int outputLayerNeuronNumber,
               int hiddenLayerNeuronNumber,
               double learningRate,
               int epochMaxNumber,
               Function<Double, Double> activationFunction) {
        this.inputLayerNeuronNumber = inputLayerNeuronNumber;
        this.outputLayerNeuronNumber = outputLayerNeuronNumber;
        this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber;
        this.learningRate = learningRate;
        this.errorRate = Double.POSITIVE_INFINITY;
        this.epochMaxNumber = epochMaxNumber;
        this.activationFunction = activationFunction;
        this.currentEpoch = 0;

        hiddenWeightMatrix = new Double[inputLayerNeuronNumber + 1][hiddenLayerNeuronNumber];
        outputWeightMatrix = new Double[hiddenLayerNeuronNumber + 1][outputLayerNeuronNumber];

        inputXVector = new Double[inputLayerNeuronNumber + 1];
        expectedYvector = new Double[outputLayerNeuronNumber];

        VectorGenerator vectorGenerator = new VectorGenerator();
        inputXVector = vectorGenerator.generate(inputXVector);
        expectedYvector = vectorGenerator.generate(expectedYvector);

        inputXVector[0] = bias;

        MatrixGenerator generator = new MatrixGenerator();
        hiddenWeightMatrix = generator.generate(hiddenWeightMatrix);
        outputWeightMatrix = generator.generate(outputWeightMatrix);
    }

    public ANN(int inputLayerNeuronNumber,
               int outputLayerNeuronNumber,
               int hiddenLayerNeuronNumber,
               double learningRate,
               int epochMaxNumber,
               Function<Double, Double> activationFunction,
               Double[] inputXVector,
               Double[] expectedYvector) {
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

        hiddenWeightMatrix = new Double[inputLayerNeuronNumber + 1][hiddenLayerNeuronNumber];
        outputWeightMatrix = new Double[hiddenLayerNeuronNumber + 1][outputLayerNeuronNumber];

        MatrixGenerator generator = new MatrixGenerator();
        hiddenWeightMatrix = generator.generate(hiddenWeightMatrix);
        outputWeightMatrix = generator.generate(outputWeightMatrix);
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
}
