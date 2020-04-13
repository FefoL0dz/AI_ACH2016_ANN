package com.company.tools.graph;

// import org.jfree.chart.ChartFactory;
// import org.jfree.chart.ChartPanel;
// import org.jfree.chart.JFreeChart;
// import org.jfree.data.general.DefaultPieDataset;

// import javax.swing.*;

// /**
//  Created by: Felipe Lodes in 11/04/2020.
//  Discipline: ACH2016 - "Inteligência Artificial"
//  Professor: Sarajane

//  *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

//  **/

// public class MainPlotterTester extends JFrame {

//     public static void main(String[] args) {
//         new MainPlotterTester();
//     }

//     public void createChart() {
//         DefaultPieDataset test = new DefaultPieDataset();
//         test.setValue("Test", 9);
//         test.setValue("Luvre", 10);
//         JFreeChart chart = ChartFactory.createPieChart("AOO", test, true, true, false);
//         ChartPanel panel = new ChartPanel(chart);
//         add(panel);
//     }

//     public MainPlotterTester() {
//         setDefaultCloseOperation(EXIT_ON_CLOSE);
//         setTitle("Teste");
//         setSize(900, 700);
//         setLocationRelativeTo(null);
//         createChart();
//         setVisible(true);
//     }
// }

import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class MainPlotterTester extends ApplicationFrame {

    public MainPlotterTester( String applicationTitle, String chartTitle ) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
            chartTitle ,
            "X" ,
            "Y" ,
            createDataset() ,
            PlotOrientation.VERTICAL ,
            true , true , false);
            
        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        // renderer.setSeriesPaint( 0 , Color.RED );
        // renderer.setSeriesPaint( 1 , Color.GREEN );
        // renderer.setSeriesPaint( 2 , Color.YELLOW );
        // renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        // renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
        // renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);

        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);

        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesVisible(2, true); 
        plot.setRenderer( renderer ); 
        setContentPane( chartPanel ); 
    }
    
    private XYDataset createDataset( ) {
        final XYSeries AND = new XYSeries("AND");          
        AND.add(-1, -1);
        AND.add(-1, 1);
        AND.add(1, -1);
        AND.add(1, 1);
        
        final XYSeries OR = new XYSeries("OR");          
        OR.add( 1.0 , 4.0 );
        OR.add( 2.0 , 5.0 );
        OR.add( 3.0 , 6.0 );
        
        final XYSeries XOR = new XYSeries("XOR");          
        XOR.add( 3.0 , 4.0 );
        XOR.add( 4.0 , 5.0 );
        XOR.add( 5.0 , 4.0 );
        
        final XYSeriesCollection dataset = new XYSeriesCollection();          
        dataset.addSeries(AND);
        dataset.addSeries(OR);
        dataset.addSeries(XOR);
        return dataset;
    }

    public static void main( String[ ] args ) {
        MainPlotterTester chart = new MainPlotterTester("Window Name",
            "Title");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true ); 
    }
}