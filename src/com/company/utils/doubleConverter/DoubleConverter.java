package com.company.utils.doubleConverter;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class DoubleConverter {

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

    public static Double toDouble(double value) {
        return Double.valueOf(value);
    }
}
