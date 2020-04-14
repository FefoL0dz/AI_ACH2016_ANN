package com.company.tools.graph;

import com.company.multiLayerPerceptron.ANN;
import com.company.tools.IO.BaseIOHandler;
import com.company.tools.IO.FileURIComponents;
import com.company.utils.exception.NotYetImplementedException;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class Plotter extends BaseIOHandler {

    double[] teste1 = {20, 50};
    double[] teste2 = {30, 80};

    double[] teste3 = {90, 25};
    double[] teste4 = {15, 78};

    final XYChart chart = QuickChart.getChart("problemXOR.csv", "X", "Y", "problemXOR.csv", teste1, teste2);
    final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);

    public Plotter() {
        super(FileURIComponents.GRAPHS_FOLDER_NAME);
        sw.displayChart();
    }

    public static Plotter getInstance() {
        return new Plotter();
    }

    public void plot(ANN neuralNetwork) {
        System.out.println(neuralNetwork.getInputXVector());
        //neuralNetwork.getHiddenWeightMatrix();
        //System.out.println(neuralNetwork.getCurrentEpoch());

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        this.chart.updateXYSeries("problemXOR.csv", DoubleObjectToDoublePrimitive(), DoubleObjectToDoublePrimitive(), null);
        sw.repaintChart();
    }

    public double[] DoubleObjectToDoublePrimitive(Double[] array1) {
        double[] array2 = new double[array1.length];

        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[i].doubleValue();
        }

        return array2;
    }
}
