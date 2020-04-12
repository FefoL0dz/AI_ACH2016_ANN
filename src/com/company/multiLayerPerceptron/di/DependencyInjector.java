package com.company.multiLayerPerceptron.di;

import com.company.multiLayerPerceptron.ANN;
import com.company.tools.IO.input.InputReader;
import com.company.tools.generator.MatrixGenerator;
import com.company.tools.math.IFunction;
import com.company.tools.math.Sigmoid;
import com.company.tools.math.Tanh;
import com.company.utils.doubleConverter.DoubleConverter;
import com.company.utils.string.StringUtils;

import java.util.List;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class DependencyInjector {

    DependencyInjector.Builder builder;

    public static DependencyInjector createInstanceFromFileDependency(double learningRate, int epochNumber, String functionTag, int hiddenLayerSize, String fileDependency) {
        return new DependencyInjector(learningRate, epochNumber, functionTag, hiddenLayerSize, fileDependency);
    }

    public static DependencyInjector createInstanceFromLocalTest(double learningRate, int epochNumber, String functionTag, int hiddenLayerSize, double[][] inputs, double[][] outputs) {
        return new DependencyInjector(learningRate, epochNumber, functionTag, hiddenLayerSize, inputs, outputs);
    }

    public DependencyInjector(double learningRate, int epochNumber, String functionTag, int hiddenLayerSize, String fileDependency) {
        List<List<Double>> input = new InputReader(fileDependency).readInput();
        List<List<Double>> output = new InputReader(fileDependency).readOutput();

        Double[][] inputs = DoubleConverter.doubleFromLists(input);
        Double[][] outputs = DoubleConverter.doubleFromLists(output);

        Double[][] hiddenWeightMatrix = new Double[inputs[0].length][hiddenLayerSize]; //{{-0.1, 0.1, -0.1}, {-0.1, 0.1, 0.1}, {0.1, -0.1, -0.1}};
        Double[][] outputWeightMatrix = new Double[hiddenLayerSize][outputs[0].length]; //{{-0.1, 0.1, 0.0, 0.1}, {0.1, -0.1, 0.1, -0.1}};

        MatrixGenerator generator = new MatrixGenerator();
        hiddenWeightMatrix = generator.generate(hiddenWeightMatrix);
        outputWeightMatrix = generator.generate(outputWeightMatrix);

        constructBuilder(inputs[0].length,
                outputs[0].length,
                hiddenLayerSize,
                learningRate,
                epochNumber,
                injectFunction(functionTag),
                inputs,
                outputs,
                hiddenWeightMatrix,
                outputWeightMatrix);
    }

    public DependencyInjector(double learningRate, int epochNumber, String functionTag, int hiddenLayerSize, double[][] inputs, double[][] outputs) {
        Double[][] hiddenWeightMatrix = new Double[inputs[0].length][hiddenLayerSize]; //{{-0.1, 0.1, -0.1}, {-0.1, 0.1, 0.1}, {0.1, -0.1, -0.1}};
        Double[][] outputWeightMatrix = new Double[hiddenLayerSize][outputs[0].length]; //{{-0.1, 0.1, 0.0, 0.1}, {0.1, -0.1, 0.1, -0.1}};

        MatrixGenerator generator = new MatrixGenerator();
        hiddenWeightMatrix = generator.generate(hiddenWeightMatrix);
        outputWeightMatrix = generator.generate(outputWeightMatrix);

        constructBuilder(inputs[0].length,
                outputs[0].length,
                hiddenLayerSize,
                learningRate,
                epochNumber,
                injectFunction(functionTag),
                DoubleConverter.toDouble(inputs),
                DoubleConverter.toDouble(outputs),
                hiddenWeightMatrix,
                outputWeightMatrix);
    }

    private void constructBuilder(int inputLayerNeuronNumber,
                                  int outputLayerNeuronNumber,
                                  int hiddenLayerNeuronNumber,
                                  double learningRate,
                                  int epochNumber,
                                  IFunction<Double, Double> injectedFunction,
                                  Double[][] inputXVector,
                                  Double[][] expectedYVectors,
                                  Double[][] hiddenWeightMatrix,
                                  Double[][] outputWeightMatrix) {
        builder = new Builder()
                .withInputLayerNeuronNumber(inputLayerNeuronNumber)
                .withOutputLayerNeuronNumber(outputLayerNeuronNumber)
                .withHiddenLayerNeuronNumber(hiddenLayerNeuronNumber)
                .withLearningRate(learningRate)
                .withEpochMaxNumber(epochNumber)
                .withActivationFunction(injectedFunction)
                .withInputXVectors(inputXVector)
                .withExpectedYVector(expectedYVectors)
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

        private Double inputXVector[][];
        private Double expectedYVectors[][];

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

        public Builder withInputXVectors(Double[][] input) {
            this.inputXVector = input;
            return this;
        }

        public Builder withExpectedYVector(Double[][] output) {
            this.expectedYVectors = output;
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
                    outputLayerNeuronNumber,
                    hiddenLayerNeuronNumber,
                    learningRate,
                    epochMaxNumber,
                    activationFunction,
                    inputXVector,
                    expectedYVectors,
                    hiddenWeightMatrix,
                    outputWeightMatrix);
        }
    }
}
