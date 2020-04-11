package com.company.tools.IO.output;

import com.company.tools.IO.BaseIOHandler;
import com.company.tools.IO.FileURIComponents;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class OutputPrinter extends BaseIOHandler {

    public OutputPrinter(String fileExtension) {
        super(FileURIComponents.OUTPUT_FOLDER_NAME, fileExtension);
    }
}
