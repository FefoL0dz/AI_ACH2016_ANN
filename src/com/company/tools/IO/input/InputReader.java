package com.company.tools.IO.input;

import com.company.tools.IO.BaseIOHandler;
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
    public InputReader(String path, String fileExtension) {
        super(path, fileExtension);
    }

    public String[][] read() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path + fileExtension));

        List<String[]> input = new ArrayList<String[]>();
        int inputRowStringLength = 0;

        while(sc.hasNextLine()) {
            String[] inputRowString = sc.nextLine().split(",");
            inputRowStringLength = inputRowString.length;
            inputRowString[0] = inputRowString[0].replace("\uFEFF","");

            input.add(inputRowString);
        }
        
        sc.close();

        String[][] inputArray = new String[input.size()][inputRowStringLength];
        input.toArray(inputArray);

        return inputArray;
    }
}
