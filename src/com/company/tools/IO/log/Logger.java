package com.company.tools.IO.log;

import com.company.multiLayerPerceptron.ANN;
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
        throw new NotYetImplementedException();
    }

    public void logException(Exception e) {
        throw new NotYetImplementedException();
    }
}
