package com.mjc.school.controller.exceptions;

import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.mjc.school.service.exception.ServiceExceptionCode.RESOURCE_NOT_FOUND;
import static com.mjc.school.service.exception.ServiceExceptionCode.VALIDATION_EXCEPTION;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<CustomErrorResponse> handleValidationException(ValidationException e, WebRequest request) {
        return build(VALIDATION_EXCEPTION.getCode(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException e, WebRequest request) {
        return build(RESOURCE_NOT_FOUND.getCode(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<CustomErrorResponse> build(String code, String errorMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(new CustomErrorResponse(code, errorMessage), httpStatus);
    }
}
