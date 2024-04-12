package com.mjc.school.service.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message){
        super(message);
    }
}
