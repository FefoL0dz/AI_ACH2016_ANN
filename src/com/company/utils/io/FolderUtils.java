package com.company.utils.io;

import com.company.tools.IO.BaseIOHandler;
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

public class FolderUtils {

    public static void createFolder(final String path) {
        if(!isFolderCreated(path)) {
            boolean success = (new File(path)).mkdirs();

            if(!success)
                throw new UnableToCreateFolderException();
        }
    }

    public static boolean isFolderCreated(final String path) {
        return Files.exists(Paths.get(path));
    }
}
