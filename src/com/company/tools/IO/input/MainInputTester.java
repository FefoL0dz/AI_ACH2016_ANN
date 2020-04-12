package com.company.tools.IO.input;

import java.io.FileNotFoundException;
import com.company.tools.IO.input.InputReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class MainInputTester {

    public static void main(String[] args) throws FileNotFoundException {
        InputReader inputReader = new InputReader("problemAND", ".csv");
        List<List<Double>> input = inputReader.read();

        Iterator iterator = input.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}