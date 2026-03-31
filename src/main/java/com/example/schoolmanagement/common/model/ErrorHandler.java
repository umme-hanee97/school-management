package com.example.schoolmanagement.common.model;

public class ErrorHandler extends Exception{
    public ErrorHandler(String message) {
        super(message);
    }

    public ErrorHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
