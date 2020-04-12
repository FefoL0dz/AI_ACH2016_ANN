package com.company.tools.graph;

import com.company.multiLayerPerceptron.ANN;
import com.company.tools.IO.BaseIOHandler;
import com.company.tools.IO.FileURIComponents;
import com.company.utils.exception.NotYetImplementedException;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class Plotter extends BaseIOHandler {

    public Plotter() {
        super(FileURIComponents.GRAPHS_FOLDER_NAME);
    }

    public static Plotter getInstance() {
        return new Plotter();
    }

    public void plot(ANN neuralNetwork) {
        throw new NotYetImplementedException();
    }
}
