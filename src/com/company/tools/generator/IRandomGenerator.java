package com.company.tools.generator;

import java.util.Random;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public interface IRandomGenerator<E, T> {

    E generate(T value);

    default Double generateDouble() {
        return new Random().nextDouble();
    }

    default int generateInt() {
        return new Random().nextInt();
    }
}
