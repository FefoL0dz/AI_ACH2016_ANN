package com.company.tools.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;

/**
 Created by: Felipe Lodes in 11/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class MainPlotterTester extends JFrame {

    public static void main(String[] args) {
        new MainPlotterTester();
    }

    public void createChart() {
        DefaultPieDataset test = new DefaultPieDataset();
        test.setValue("Test", 9);
        test.setValue("Luvre", 10);
        JFreeChart chart = ChartFactory.createPieChart("AOO", test, true, true, false);
        ChartPanel panel = new ChartPanel(chart);
        add(panel);
    }

    public MainPlotterTester() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Teste");
        setSize(900, 700);
        setLocationRelativeTo(null);
        createChart();
        setVisible(true);
    }
}
