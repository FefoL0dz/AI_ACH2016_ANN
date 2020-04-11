package com.company.tools.IO;

/**
 * Created by: Felipe Lodes in 08/04/2020.
 * Discipline: ACH2016 - "Inteligência Artificial"
 * Professor: Sarajane
 * <p>
 * Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*
 **/

public abstract class BaseIOHandler {

    //TODO: IHMO We should use the own project repository as folder to our resource files (logs, input files and graphs)
    protected String path;

    public static final String PROJECT_ROOT_FOLDER = "C:\\Users\\Felipe Lodes\\Desktop\\AI_ACH2016_ANN\\resources";
    //public static final String PROJECT_ROOT_FOLDER = "resources";

    protected String fileExtension;

    public BaseIOHandler(String path, String fileExtension) {
        this.path = PROJECT_ROOT_FOLDER + "\\"+ path;
        this.fileExtension = fileExtension;
    }

    public BaseIOHandler(String path) {
        this.path = PROJECT_ROOT_FOLDER + "\\"+ path;
    }
}

