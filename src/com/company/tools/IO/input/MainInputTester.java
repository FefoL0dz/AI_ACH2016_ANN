package com.company.tools.IO.input;

import java.util.List;
import java.util.Iterator;

public class MainInputTester {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("caracteres-limpo.csv");
        List<List<Double>> input = inputReader.readInput();
        List<List<Character>> output = inputReader.readCharOutput();
        //List<List<Double>> output = inputReader.readDoubleOutput();

        Iterator iteratorIn = input.iterator();
        Iterator iteratorOut = output.iterator();

        System.out.println("Inputs");
        while(iteratorIn.hasNext()) {
            System.out.println(iteratorIn.next());
        }

        System.out.println("Outputs");
        while(iteratorOut.hasNext()) {
            System.out.println(iteratorOut.next());
        }
    }
}