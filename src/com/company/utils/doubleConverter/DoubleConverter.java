package com.company.utils.doubleConverter;

import java.util.Iterator;
import java.util.List;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class DoubleConverter {

    public static Double[][] doubleFromLists(List<List<Double>> list) {
        double[][] matrix = new double[list.size()][];

        Iterator iterator = list.iterator();

        int i = 0;
        while(iterator.hasNext()) {
            matrix[i] = toDouble((List<Double>)iterator.next());
            i++;
        }
        //here we could use Double object instead primitive double to avoid calling toDouble method (less execution processing)
        return toDouble(matrix);
    }

    public static Double[][] toDouble(double[][] matrix) {
        Double[][] result = new Double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = toDouble(matrix[i]);
        }
        return result;
    }

    public static Double[] toDouble(double[] list) {
        Double[] result = new Double[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = toDouble(list[i]);
        }
        return result;
    }

    public static double[] toDouble(List<Double> list) {
        double[] ret = new double[list.size()];
        Iterator<Double> iterator = list.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().doubleValue();
        }
        return ret;
    }

    public static Double toDouble(double value) {
        return Double.valueOf(value);
    }
}
