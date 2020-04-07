package com.company.multiLayerPerceptron.di;

import com.company.multiLayerPerceptron.ANN;
import com.company.utils.math.Function;
import com.company.utils.math.Sigmoid;
import com.company.utils.math.Tanh;
import com.company.utils.string.StringUtils;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class DependencyInjector {

    private String functionTag;

    private int inputLayerNeuronNumber;
    private int outputLayerNeuronNumber;
    private int hiddenLayerNeuronNumber;

    private double learningRate;
    private int epochNumber;

    public static DependencyInjector createInstance(int inputLayerNeuronNumber,
                                                    int outputLayerNeuronNumber,
                                                    int hiddenLayerNeuronNumber,
                                                    double learningRate,
                                                    int epochNumber,
                                                    String functionTag) {
        return new DependencyInjector(inputLayerNeuronNumber, outputLayerNeuronNumber, hiddenLayerNeuronNumber, learningRate, epochNumber, functionTag);
    }

    public DependencyInjector(int inputLayerNeuronNumber, int outputLayerNeuronNumber, int hiddenLayerNeuronNumber, double learningRate, int epochNumber, String functionTag) {
        this.functionTag = functionTag;
        this.inputLayerNeuronNumber = inputLayerNeuronNumber;
        this.outputLayerNeuronNumber = outputLayerNeuronNumber;
        this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber;
        this.learningRate = learningRate;
        this.epochNumber = epochNumber;
    }

    public ANN inject() {
        return new ANN(inputLayerNeuronNumber, outputLayerNeuronNumber, hiddenLayerNeuronNumber, learningRate, epochNumber, injectFunction());
    }

    private Function<Double, Double> injectFunction() {
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
}
