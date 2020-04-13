package com.company.utils.exception;

/**
 Created by: Felipe Lodes in 11/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class UnableToWriteFileException extends RuntimeException {

    private final static String message = "Not able to write file!";

    public UnableToWriteFileException() {
        super(message);
    }

    public UnableToWriteFileException(String errorMessage) {
        super(message + " " + errorMessage);
    }
}