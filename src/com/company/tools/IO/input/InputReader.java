package com.company.tools.IO.input;

import com.company.tools.IO.BaseIOHandler;
import com.company.tools.IO.FileURIComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class InputReader extends BaseIOHandler {

    public InputReader(String fileName, String fileExtension) {
        super(FileURIComponents.INPUT_FOLDER_NAME + "\\" + fileName, fileExtension);
    }

    public List<List<Double>> readInput() throws FileNotFoundException {

        Scanner sc = new Scanner(new File(path + fileExtension));

        List<List<Double>> input = new ArrayList<List<Double>>();

        while(sc.hasNextLine()) {
            String[] inputRowString = sc.nextLine().split(",");
            List inputRow = new ArrayList();

            // Add X (Input Neuron Layer Values)
            for (int i = 0; i < inputRowString.length - 1; i++) {
                inputRow.add(Double.parseDouble(inputRowString[i].replace("\uFEFF","")));
            }

            input.add(inputRow);
        }
        
        sc.close();

        return input;
    }

    public List<Double> readOutput() throws FileNotFoundException {

        Scanner sc = new Scanner(new File(path + fileExtension));

        List<Double> input = new ArrayList<>();

        while(sc.hasNextLine()) {
            String[] inputRowString = sc.nextLine().split(",");

            // Add Y (Output Neuron Layer Desired Values)
            input.add(Double.parseDouble(inputRowString[inputRowString.length - 1]));
        }

        sc.close();

        return input;
    }
}
