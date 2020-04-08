package com.company.utils.exception;

public class NotYetImplementedException extends RuntimeException {
    private final static String message = "Not yet implemented!";
    public NotYetImplementedException() {
        super(message);
    }
}
