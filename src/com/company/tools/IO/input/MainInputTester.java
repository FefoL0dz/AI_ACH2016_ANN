package com.company.tools.IO.input;

import java.io.FileNotFoundException;
import com.company.tools.IO.input.InputReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class MainInputTester {

    public static void main(String[] args) throws FileNotFoundException {
        InputReader inputReader = new InputReader("com/company/tools/IO/input/inputFiles/problemAND", ".csv");
        String[][] input = inputReader.read();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                System.out.println(input[i][j]);
            }
        }
    }
}