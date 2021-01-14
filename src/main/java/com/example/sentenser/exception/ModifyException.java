package com.example.sentenser.exception;

public class ModifyException extends Exception{
    private String message;

    public ModifyException(String message) {
        super(message);
    }

    public String getMessage() {
        return message;
    }
}
