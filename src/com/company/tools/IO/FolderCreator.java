package com.company.tools.IO;

import com.company.utils.exception.UnableToCreateFolderException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 Created by: Felipe Lodes in 11/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class FolderCreator extends BaseIOHandler {

    public FolderCreator(String path) {
        super(path);
    }

    public void createFolder() {
        if(!isFolderCreated()) {
            boolean success = (new File(path)).mkdirs();

            if(!success)
                throw new UnableToCreateFolderException();
        }
    }

    public boolean isFolderCreated() {
        return Files.exists(Paths.get(path));
    }
}
