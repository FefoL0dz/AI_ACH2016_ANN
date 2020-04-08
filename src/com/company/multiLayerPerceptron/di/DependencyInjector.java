package com.company.multiLayerPerceptron.di;

import com.company.multiLayerPerceptron.ANN;
import com.company.generator.MatrixGenerator;
import com.company.generator.VectorGenerator;
import com.company.math.IFunction;
import com.company.math.Sigmoid;
import com.company.math.Tanh;
import com.company.utils.string.StringUtils;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class DependencyInjector {

    DependencyInjector.Builder builder;

    private final double bias = 1;

    public static DependencyInjector createInstance(int inputLayerNeuronNumber,
                                                    int outputLayerNeuronNumber,
                                                    int hiddenLayerNeuronNumber,
                                                    double learningRate,
                                                    int epochNumber,
                                                    String functionTag) {
        return new DependencyInjector(inputLayerNeuronNumber,
                outputLayerNeuronNumber,
                hiddenLayerNeuronNumber,
                learningRate,
                epochNumber,
                functionTag);
    }

    public static DependencyInjector createInstance(int inputLayerNeuronNumber,
                                                    int outputLayerNeuronNumber,
                                                    int hiddenLayerNeuronNumber,
                                                    double learningRate,
                                                    int epochMaxNumber,
                                                    String functionTag,
                                                    Double[] inputXVector,
                                                    Double[] expectedYvector) {
        return new DependencyInjector(inputLayerNeuronNumber,
                outputLayerNeuronNumber,
                hiddenLayerNeuronNumber,
                learningRate,
                epochMaxNumber,
                functionTag,
                inputXVector,
                expectedYvector);
    }

    public DependencyInjector(int inputLayerNeuronNumber,
                              int outputLayerNeuronNumber,
                              int hiddenLayerNeuronNumber,
                              double learningRate,
                              int epochNumber,
                              String functionTag) {
        Double[][] hiddenWeightMatrix = new Double[inputLayerNeuronNumber + 1][hiddenLayerNeuronNumber];
        Double[][] outputWeightMatrix = new Double[hiddenLayerNeuronNumber + 1][outputLayerNeuronNumber];

        MatrixGenerator generator = new MatrixGenerator();
        hiddenWeightMatrix = generator.generate(hiddenWeightMatrix);
        outputWeightMatrix = generator.generate(outputWeightMatrix);

        Double[] inputXVector = new Double[inputLayerNeuronNumber + 1];
        Double[] expectedYvector = new Double[outputLayerNeuronNumber];

        VectorGenerator vectorGenerator = new VectorGenerator();
        inputXVector = vectorGenerator.generate(inputXVector);
        expectedYvector = vectorGenerator.generate(expectedYvector);

        inputXVector[0] = bias;

        constructBuilder(inputLayerNeuronNumber,
                outputLayerNeuronNumber,
                hiddenLayerNeuronNumber,
                learningRate,
                epochNumber,
                injectFunction(functionTag),
                inputXVector,
                expectedYvector,
                hiddenWeightMatrix,
                outputWeightMatrix);
    }

    public DependencyInjector(int inputLayerNeuronNumber,
                              int outputLayerNeuronNumber,
                              int hiddenLayerNeuronNumber,
                              double learningRate,
                              int epochNumber,
                              String functionTag,
                              Double[] inputXVector,
                              Double[] expectedYvector) {
        Double[][] hiddenWeightMatrix = new Double[inputLayerNeuronNumber + 1][hiddenLayerNeuronNumber];
        Double[][] outputWeightMatrix = new Double[hiddenLayerNeuronNumber + 1][outputLayerNeuronNumber];

        MatrixGenerator generator = new MatrixGenerator();
        hiddenWeightMatrix = generator.generate(hiddenWeightMatrix);
        outputWeightMatrix = generator.generate(outputWeightMatrix);

        constructBuilder(inputLayerNeuronNumber,
                outputLayerNeuronNumber,
                hiddenLayerNeuronNumber,
                learningRate,
                epochNumber,
                injectFunction(functionTag),
                inputXVector,
                expectedYvector,
                hiddenWeightMatrix,
                outputWeightMatrix);
    }

    public DependencyInjector(int inputLayerNeuronNumber,
                              int outputLayerNeuronNumber,
                              int hiddenLayerNeuronNumber,
                              double learningRate,
                              int epochNumber,
                              String functionTag,
                              Double[] inputXVector,
                              Double[] expectedYvector,
                              Double[][] hiddenWeightMatrix,
                              Double[][] outputWeightMatrix) {

        constructBuilder(inputLayerNeuronNumber,
                outputLayerNeuronNumber,
                hiddenLayerNeuronNumber,
                learningRate,
                epochNumber,
                injectFunction(functionTag),
                inputXVector,
                expectedYvector,
                hiddenWeightMatrix,
                outputWeightMatrix);
    }

    private void constructBuilder(int inputLayerNeuronNumber,
                                  int outputLayerNeuronNumber,
                                  int hiddenLayerNeuronNumber,
                                  double learningRate,
                                  int epochNumber,
                                  IFunction<Double,
                                                                            Double> injectedFunction,
                                  Double[] inputXVector,
                                  Double[] expectedYvector,
                                  Double[][] hiddenWeightMatrix,
                                  Double[][] outputWeightMatrix) {
        builder = new Builder()
                .withInputLayerNeuronNumber(inputLayerNeuronNumber)
                .withOutputLayerNeuronNumber(outputLayerNeuronNumber)
                .withHiddenLayerNeuronNumber(hiddenLayerNeuronNumber)
                .withLearningRate(learningRate)
                .withEpochMaxNumber(epochNumber)
                .withActivationFunction(injectedFunction)
                .withInputXVector(inputXVector)
                .withExpectedYVector(expectedYvector)
                .withHiddenWeightMatrix(hiddenWeightMatrix)
                .withOutputWeightMatrix(outputWeightMatrix);
    }

    public ANN inject() {
        return builder.build();
    }

    private IFunction<Double, Double> injectFunction(String functionTag) {
        if (StringUtils.isNullOrEmpty(functionTag)) {
            return new Sigmoid();
        }

        switch (functionTag) {
            case Sigmoid.TAG:
                return new Sigmoid();
            case Tanh.TAG:
                return new Tanh();
            default:
                return new Sigmoid();
        }
    }

    private class Builder {

        private int inputLayerNeuronNumber;
        private int outputLayerNeuronNumber;
        private int hiddenLayerNeuronNumber;

        private double learningRate;
        private int epochMaxNumber;

        private Double[][] hiddenWeightMatrix;
        private Double[][] outputWeightMatrix;

        IFunction<Double, Double> activationFunction;

        private Double inputXVector[];
        private Double expectedYvector[];

        public Builder withInputLayerNeuronNumber(int inputLayerNeuronNumber) {
            this.inputLayerNeuronNumber = inputLayerNeuronNumber;
            return this;
        }

        public Builder withOutputLayerNeuronNumber(int outputLayerNeuronNumber) {
            this.outputLayerNeuronNumber = outputLayerNeuronNumber;
            return this;
        }

        public Builder withHiddenLayerNeuronNumber(int hiddenLayerNeuronNumber) {
            this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber;
            return this;
        }

        public Builder withLearningRate(double learningRate) {
            this.learningRate = learningRate;
            return this;
        }

        public Builder withEpochMaxNumber(int epochMaxNumber) {
            this.epochMaxNumber = epochMaxNumber;
            return this;
        }

        public Builder withActivationFunction(IFunction<Double, Double> func) {
            this.activationFunction = func;
            return this;
        }

        public Builder withInputXVector(Double[] input) {
            this.inputXVector = input;
            return this;
        }

        public Builder withExpectedYVector(Double[] output) {
            this.expectedYvector = output;
            return this;
        }

        public Builder withHiddenWeightMatrix(Double[][] hiddenWeightMatrix) {
            this.hiddenWeightMatrix = hiddenWeightMatrix;
            return this;
        }

        public Builder withOutputWeightMatrix(Double[][] outputWeightMatrix) {
            this.outputWeightMatrix = outputWeightMatrix;
            return this;
        }

        public ANN build() {
            return new ANN(inputLayerNeuronNumber,
                    hiddenLayerNeuronNumber,
                    outputLayerNeuronNumber,
                    learningRate,
                    epochMaxNumber,
                    activationFunction,
                    inputXVector,
                    expectedYvector,
                    hiddenWeightMatrix,
                    outputWeightMatrix);
        }
    }
}
