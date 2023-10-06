package com.discretask.exceptions;

public class invalidDateException extends Exception {
    String message;

    public invalidDateException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
