package com.example.web_shopapi.exception;

public class ForeignKeyException extends RuntimeException {

    public ForeignKeyException(String message) {
        super(message);
    }

    public ForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

}