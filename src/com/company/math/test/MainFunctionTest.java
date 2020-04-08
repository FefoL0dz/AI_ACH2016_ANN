package com.company.math.test;

import com.company.math.IFunction;
import com.company.math.Sigmoid;

public class MainFunctionTest {
    public static void main(String[] args) {
        IFunction<Double, Double> function;
        function = new Sigmoid();
        print(function.execute(-0.1));
        print(function.execute(0.1));
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
