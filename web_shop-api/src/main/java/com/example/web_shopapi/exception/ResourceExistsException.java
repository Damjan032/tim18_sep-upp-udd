package com.example.web_shopapi.exception;

public class ResourceExistsException extends RuntimeException {

    public ResourceExistsException(String message) {
        super(message);
    }

    public ResourceExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
