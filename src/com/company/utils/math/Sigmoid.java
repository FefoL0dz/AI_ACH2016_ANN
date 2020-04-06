package com.company.utils.math;

public class Sigmoid implements Function<Double, Double> {
    @Override
    public Double execute() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double execute(Double value) {
        return (1/( 1 + Math.pow(Math.E, (-1 * value) )));
    }

    @Override
    public Double execute(Double value1, Double value) {
        throw new UnsupportedOperationException();
    }
}
