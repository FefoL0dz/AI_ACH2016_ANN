package com.company.utils.Double;

public class DoubleConverter {

    public static Double[] toDouble(double[] list) {
        Double[] result = new Double[list.length];
        for (int i = 0; i < list.length; i++) {
            toDouble(list[i]);
        }
        return result;
    }

    public static Double toDouble(double value) {
        return Double.valueOf(value);
    }
}
