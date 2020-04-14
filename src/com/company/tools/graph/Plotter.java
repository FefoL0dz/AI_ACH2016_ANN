package com.company.tools.graph;

import com.company.multiLayerPerceptron.ANN;
import com.company.tools.IO.BaseIOHandler;
import com.company.tools.IO.FileURIComponents;
import com.company.utils.doubleConverter.DoubleConverter;

import javax.swing.*;

//import org.knowm.xchart.XChartPanel;
//import org.knowm.xchart.QuickChart;
//import org.knowm.xchart.SwingWrapper;
//import org.knowm.xchart.XYChart;

import java.awt.*;

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

    //final XYChart chart = QuickChart.getChart("problemXOR.csv", "X", "Y", "problemXOR.csv", teste1, teste2);
    //final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);

    JFrame frame = new JFrame("problemXOR.csv");
    JLabel epoch = new JLabel("epoch", JLabel.CENTER);
    //JPanel chartPanel = new XChartPanel(chart);

    public Plotter() {
        super(FileURIComponents.GRAPHS_FOLDER_NAME);
        //sw.displayChart();

        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {

                @Override
                public void run() {

                    createAndShowGUI();
                }
            }
        );
    }

    public static Plotter getInstance() {
        return new Plotter();
    }

    public void plot(ANN neuralNetwork) {
        //System.out.println(neuralNetwork.getInputXVector());
        //neuralNetwork.getHiddenWeightMatrix();
        System.out.println(neuralNetwork.getCurrentEpoch());
        epoch.setText("Epoch: " + Integer.toString(neuralNetwork.getCurrentEpoch()));

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        //this.chart.updateXYSeries("problemXOR.csv", DoubleConverter.DoubleObjectToDoublePrimitive(neuralNetwork.getInputXVector()), DoubleConverter.DoubleObjectToDoublePrimitive(neuralNetwork.getInputXVector()), null);
        //sw.repaintChart();
        frame.repaint();
    }

    private void createAndShowGUI() {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

       // frame.add(chartPanel);

        frame.add(epoch, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
    }

}
