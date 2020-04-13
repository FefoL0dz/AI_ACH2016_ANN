package com.company.utils.exception;

/**
 Created by: Felipe Lodes in 12/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class UnknownRuntimeException extends RuntimeException {

    private final static String message = "Runtime Error";

    public UnknownRuntimeException() {
        super(message);
    }

    public UnknownRuntimeException(String errorMessage) {
        super(message + ": " + errorMessage);
    }
}