package com.company.tools.IO.log;

import com.company.multiLayerPerceptron.ANN;
import com.company.tools.IO.FileURIComponents;
import com.company.tools.IO.output.OutputPrinter;

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
        createFolderIfNeeded();
        new OutputPrinter("MLP_initial_settings", FileURIComponents.TXT_EXT)
                .printMLPInitialInformation(
                        neuralNetwork.getFunctionTag(),
                        neuralNetwork.getLearningRate(),
                        neuralNetwork.getEpochMaxNumber(),
                        neuralNetwork.getHiddenLayerNeuronNumber(),
                        neuralNetwork.getFileReference(),
                        neuralNetwork.getInputLayerNeuronNumber(),
                        neuralNetwork.getOutputLayerNeuronNumber());

        new OutputPrinter("Initial_Weights", FileURIComponents.CSV_EXT)
                .printWeights(neuralNetwork.getCurrentEpoch(), neuralNetwork.getHiddenWeightMatrix(), neuralNetwork.getOutputWeightMatrix());
    }

    private void createFolderIfNeeded() {
        new OutputPrinter();
    }

    public void logIteration(ANN neuralNetwork) {
        new OutputPrinter("Iteration_log", FileURIComponents.CSV_EXT)
                .printIteration(neuralNetwork.getCurrentEpoch(),
                                neuralNetwork.getInputXVector(),
                        neuralNetwork.getExpectedYvector(),
                        neuralNetwork.getObtainedYVector(),
                        neuralNetwork.getHiddenZVector());

        new OutputPrinter("Error_by_epoch", FileURIComponents.CSV_EXT)
                .printErrorInfos(neuralNetwork.getCurrentEpoch(),
                neuralNetwork.getHiddenErrorInformation(),
                neuralNetwork.getHiddenCorrectionTerm(),
                neuralNetwork.getOutputErrorInformation(),
                neuralNetwork.getOutputCorrectionTerm());

        new OutputPrinter("weights", FileURIComponents.CSV_EXT)
                .printWeights(neuralNetwork.getCurrentEpoch(),
                neuralNetwork.getHiddenWeightMatrix(),
                neuralNetwork.getOutputWeightMatrix());
    }

    public void logResults(ANN neuralNetwork) {
        new OutputPrinter("results", FileURIComponents.CSV_EXT)
                .printWeights(neuralNetwork.getCurrentEpoch(), neuralNetwork.getOutputWeightMatrix(), neuralNetwork.getHiddenWeightMatrix());
    }

    public void logException(Exception e) {
        new OutputPrinter(FileURIComponents.ERROR_LOG_NAME, FileURIComponents.TXT_EXT)
                .printException(e.getLocalizedMessage());
    }
}
