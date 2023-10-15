package com.discretask.exceptions;

/**
 * The invalidDateException class is a custom exception that is thrown when a date is invalid.
 */
public class invalidDateException extends Exception {
    
    /**
     * The message variable represents the message of the exception.
     */
    String message;

    /**
     * This is a constructor for an invalidDateException that initializes the message variable.
     * 
     * @param message The message parameter is of type String, which means it can be any type of String.
     */
    public invalidDateException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * The function returns the message of the exception.
     * 
     * @return The method is returning the value of the variable "message".
     */
    public String getMessage() {
        return message;
    }
}
