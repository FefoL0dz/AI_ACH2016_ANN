package com.company.tools.IO.input;

import java.util.List;
import java.util.Iterator;

public class MainInputTester {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("problemAND", ".csv");
        List<List<Double>> input = inputReader.readInput();
        List<List<Double>> output = inputReader.readOutput();

        Iterator iteratorIn = input.iterator();
        Iterator iteratorOut = output.iterator();

        while(iteratorIn.hasNext()) {
            System.out.println(iteratorIn.next());
        }

        while(iteratorOut.hasNext()) {
            System.out.println(iteratorOut.next());
        }
    }
}