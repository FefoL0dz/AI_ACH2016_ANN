package com.company.tools.IO;

import com.company.utils.io.FolderUtils;

/**
 * Created by: Felipe Lodes in 08/04/2020.
 * Discipline: ACH2016 - "Inteligência Artificial"
 * Professor: Sarajane
 *
 * Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*
 **/

public abstract class BaseIOHandler {

    protected String path = "";

    public BaseIOHandler(String path, String fileExtension) {
        this.path = FileURIComponents.PROJECT_ROOT_FOLDER + "\\"+ path + fileExtension;
    }

    public BaseIOHandler(String path) {
        this.path = FileURIComponents.PROJECT_ROOT_FOLDER + "\\"+ path;
    }
}

