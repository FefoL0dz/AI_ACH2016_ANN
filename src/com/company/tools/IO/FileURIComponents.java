package com.company.tools.IO;

public interface FileURIComponents {
    String TXT_EXT = ".txt";
    String CSV_EXT = ".csv";

    String C_DIR = "C:";
    String D_DIR = "D:";

    String USERS = "Users";

    //TODO: Improve this process getting project path automatically
    String PROFILE_USER = "Felipe Lodes";

    String DESKTOP = "Desktop";
    String PROJECT_NAME = "AI_ACH2016_ANN";

    String RESOURCES_FOLDER_NAME = "resources";
    String LOGS_FOLDER_NAME = "Logs";
    String INPUT_FOLDER_NAME = "Inputs";
    String OUTPUT_FOLDER_NAME = "Outputs";
    String GRAPHS_FOLDER_NAME = "Graphs";

    String PROJECT_ROOT_FOLDER = C_DIR + "\\" + USERS + "\\" + PROFILE_USER + "\\" + DESKTOP + "\\"+ PROJECT_NAME + "\\" + RESOURCES_FOLDER_NAME;
}
