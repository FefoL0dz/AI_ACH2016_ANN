package com.company.utils.exception;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class NotYetImplementedException extends RuntimeException {

    private final static String message = "Not yet implemented!";

    public NotYetImplementedException() {
        super(message);
    }
}
