package com.company.tools.math.test;

import com.company.tools.math.IFunction;
import com.company.tools.math.Sigmoid;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

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
