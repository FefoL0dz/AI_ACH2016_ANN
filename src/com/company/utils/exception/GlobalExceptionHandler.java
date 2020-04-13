package com.company.utils.exception;

import com.company.multiLayerPerceptron.ANN;
import com.company.tools.IO.log.Logger;

/**
 Created by: Felipe Lodes in 08/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class GlobalExceptionHandler {

    private Logger logger;

    public GlobalExceptionHandler() {
        this.logger = Logger.getInstance();
    }

    public static void handle(ANN neuralNetwork, Exception error) {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
//        handler.logger.logNeuralNetworkInfo(neuralNetwork);
//        handler.logger.logException(error);
        throw new UnknownRuntimeException(error.getLocalizedMessage());
    }
}
