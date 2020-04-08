package com.company.tools.math;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public interface IFunction<E, T> {
    E execute();
    E execute(T value);
    E execute(T value1, T value);
    E derivative(T value);
}
