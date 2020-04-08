package com.company.math;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class Tanh implements IFunction<Double, Double> {

    public static final String TAG = "Tanh";

    @Override
    public Double execute() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double execute(Double value) {
        return (2/( 1 + Math.pow(Math.E, (-2 * value) ))) - 1;
    }

    @Override
    public Double execute(Double value1, Double value) {
        throw new UnsupportedOperationException();
    }
}
