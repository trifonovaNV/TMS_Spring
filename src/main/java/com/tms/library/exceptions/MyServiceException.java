package com.tms.library.exceptions;

public class MyServiceException extends RuntimeException {
    public MyServiceException(String message) {
        super(message);
    }
}