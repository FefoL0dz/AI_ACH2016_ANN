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

    public List<List<Double>> read() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path + fileExtension));

        List<List<Double>> input = new ArrayList<List<Double>>();

        while(sc.hasNextLine()) {
            String[] inputRowString = sc.nextLine().split(",");
            List inputRow = new ArrayList();

            // Add X
            for (int i = 0; i < inputRowString.length - 1; i++) {
                inputRow.add(Double.parseDouble(inputRowString[i].replace("\uFEFF","")));
            }

            // Add Y
            inputRow.add(inputRowString[inputRowString.length - 1]);

            input.add(inputRow);
        }
        
        sc.close();

        return input;
    }
}
