package com.company.tools.generator;

/**
 Created by: Felipe Lodes in 11/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class IntGenerator implements IRandomGenerator<Integer, Integer> {

    @Override
    public Integer generate(Integer value) {
        return generateInt() % value;
    }
}
