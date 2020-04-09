package com.company.tools.IO;

/**
 Created by: Felipe Lodes in 08/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public abstract class BaseIOHandler {

    //TODO: IHMO We should use the own project repository as folder to our resource files (logs, input files and graphs)
    protected String path;

    protected String fileExtension;

    public BaseIOHandler(String path, String fileExtension) {
        this.path = path;
        this.fileExtension = fileExtension;
    }

    static class FileURIComponents {

        public static final String TXT_EXT = ".txt";
        public static final String CSV_EXT = ".csv";

        public static final String C_Dir = "c://";
        public static final String D_Dir = "d://";

        public static final String LOGS_FOLDER_NAME = "Logs";
        public static final String INPUT_FOLDER_NAME = "Inputs";
        public static final String OUTPUT_FOLDER_NAME = "Outputs";
        public static final String GRAPHS_FOLDER_NAME = "Graphs";
    }
}

