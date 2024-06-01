package com.mjc.school.controller.exceptions;

public class CustomErrorResponse {
    private final String code;
    private final String errorMessage;

    public CustomErrorResponse(final String code, final String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
