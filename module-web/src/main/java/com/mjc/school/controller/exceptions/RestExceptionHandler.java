package com.mjc.school.controller.exceptions;

import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidationException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import static com.mjc.school.service.exception.ServiceExceptionCode.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = JdbcSQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<CustomErrorResponse> handleValidationException(JdbcSQLIntegrityConstraintViolationException e, WebRequest request) {
        if (e.getMessage().contains("AUTHORS")) {
            return build(VALIDATION_EXCEPTION.getCode(), "Author name has been taken", HttpStatus.NOT_FOUND);
        }
        if (e.getMessage().contains("NEWS")) {
            return build(VALIDATION_EXCEPTION.getCode(), "News title has been taken", HttpStatus.NOT_FOUND);
        }
        if (e.getMessage().contains("TAGS")) {
            return build(VALIDATION_EXCEPTION.getCode(), "Tag name has been taken", HttpStatus.NOT_FOUND);
        }
        return build(e.getMessage(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<CustomErrorResponse> handleValidationException(ValidationException e, WebRequest request) {
        return build(VALIDATION_EXCEPTION.getCode(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException e, WebRequest request) {
        return build(RESOURCE_NOT_FOUND.getCode(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<CustomErrorResponse> handleNotFoundException(RuntimeException e, WebRequest request) {
        return build(RESOURCE_NOT_FOUND.getCode(), e.getClass().getName(), HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return null;
    }

    private ResponseEntity<CustomErrorResponse> build(String code, String errorMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(new CustomErrorResponse(code, errorMessage), httpStatus);
    }
}
