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

    //Not a util method
    public static void printOutput(Double[] vector, double threshold) {
        print("[");
        int i = 0;
        for (; i < vector.length - 1; i++)
            print((vector[i] > threshold ? 1.0 : 0.0) + ", ");
        println((vector[i] > threshold ? 1.0 : 0.0) + "]");
    }

    public static void printOutput(Double[] vector) {
        print("[");
        int i = 0;
        for (; i < vector.length - 1; i++)
            print(vector[i] + ", ");
        println(vector[i] + "]");
    }

    public static void printCharOutput(Double[] vector) {
        int indexMaxElement = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] > vector[indexMaxElement])
                indexMaxElement = i;
        }
        switch (indexMaxElement) {
            case 0: println("A"); break;
            case 1: println("B"); break;
            case 2: println("C"); break;
            case 3: println("D"); break;
            case 4: println("E"); break;
            case 5: println("J"); break;
            case 6: println("K"); break;
            default: println("X"); break;
        }
    }
}
