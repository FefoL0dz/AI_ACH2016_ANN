package com.company.tools.IO.input;

import com.company.tools.IO.BaseIOHandler;
import com.company.tools.IO.FileURIComponents;
import com.company.utils.exception.UnableToReadFileException;

import java.io.File;
import java.io.IOException;
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

    public InputReader(String fileName) {
        super(FileURIComponents.INPUT_FOLDER_NAME + "\\" + fileName);
    }

    public List<List<Double>> readInput() {
        Scanner sc = getScanner();

        List<List<Double>> input = new ArrayList<List<Double>>();

        while(sc.hasNextLine()) {
            String[] inputRowString = sc.nextLine().split(",");
            List inputRow = new ArrayList();

            //Add bias when reading input
            inputRow.add(Double.parseDouble("1"));

            // Add X (Input Neuron Layer Values)
            for (int i = 0; i < inputRowString.length - 1; i++) {
                inputRow.add(Double.parseDouble(inputRowString[i].replace("\uFEFF","")));
            }

            input.add(inputRow);
        }
        
        sc.close();

        return input;
    }

    public List<List<Double>> readDoubleOutput() {

        Scanner sc = getScanner();

        List<List<Double>> input = new ArrayList<>();

        while(sc.hasNextLine()) {
            List outputRow = new ArrayList();

            String[] inputRowString = sc.nextLine().split(",");

            // Add Y (Output Neuron Layer Desired Values)
            outputRow.add(Double.parseDouble(inputRowString[inputRowString.length - 1]));
            input.add(outputRow);
        }

        sc.close();
        return input;
    }

    public List<List<Character>> readCharOutput() {

        Scanner sc = getScanner();

        List<List<Character>> input = new ArrayList<>();

        while(sc.hasNextLine()) {
            List outputRow = new ArrayList();

            String[] inputRowString = sc.nextLine().split(",");

            // Add Y (Output Neuron Layer Desired Values)
            outputRow.add(inputRowString[inputRowString.length - 1].toCharArray()[0]);
            input.add(outputRow);
        }

        sc.close();
        return input;
    }

    public Scanner getScanner() {
        Scanner sc;
        try {
            sc = new Scanner(new File(path + fileExtension));
        } catch (IOException e) {
            throw new UnableToReadFileException("\n"+ e.getMessage());
        } catch (Exception e) {
            throw e;
        }
        return sc;
    }
}
