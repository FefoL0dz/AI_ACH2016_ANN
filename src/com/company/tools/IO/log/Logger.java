package com.company.tools.IO.log;

import com.company.multiLayerPerceptron.ANN;
import com.company.tools.IO.FileURIComponents;
import com.company.tools.IO.output.OutputPrinter;
import com.company.utils.exception.NotYetImplementedException;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class Logger {

    public static Logger getInstance() {
        return new Logger();
    }

    public void logNeuralNetworkInfo(ANN neuralNetwork) {
        new OutputPrinter()
                .printMLPInitialInformation(
                        neuralNetwork.getFunctionTag(),
                        neuralNetwork.getLearningRate(),
                        neuralNetwork.getEpochMaxNumber(),
                        neuralNetwork.getHiddenLayerNeuronNumber(),
                        neuralNetwork.getFileReference(),
                        neuralNetwork.getInputLayerNeuronNumber(),
                        neuralNetwork.getOutputLayerNeuronNumber());
        new OutputPrinter()
                .printWeights(neuralNetwork.getHiddenWeightMatrix(), neuralNetwork.getOutputWeightMatrix());
    }

    public void logIteration(ANN neuralNetwork) {
        new OutputPrinter()
                .printIteration(neuralNetwork.getCurrentEpoch(),
                                neuralNetwork.getInputXVector(),
                        neuralNetwork.getExpectedYvector(),
                        neuralNetwork.getObtainedYVector(),
                        neuralNetwork.getHiddenZVector(),
                        neuralNetwork.getHiddenWeightMatrix(),
                        neuralNetwork.getOutputWeightMatrix(),
                        neuralNetwork.getHiddenErrorInformation(),
                        neuralNetwork.getHiddenCorrectionTerm(),
                        neuralNetwork.getOutputErrorInformation(),
                        neuralNetwork.getOutputCorrectionTerm());
    }

    public void logException(Exception e) {
        new OutputPrinter().printException(e.getLocalizedMessage());
    }
}
