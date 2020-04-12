package com.company.tools.IO;

/**
 Created by: Felipe Lodes in 12/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class Printer {

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void printVector(Double[] vector) {
        print("[");
        int i = 0;
        for (; i < vector.length - 1; i++)
            print(vector[i] + ", ");
        println(vector[i] + "]");
    }
}
