package com.mjc.school.service.exception;

public enum ServiceExceptionCode {
    RESOURCE_NOT_FOUND("404000", " Resource not found. "),
    AUTHOR_ID_DOES_NOT_EXIST("404001", "Author with id %d does not exist."),
    NEWS_ID_DOES_NOT_EXIST("404002", "News with id %d does not exist. "),
    TAG_ID_DOES_NOT_EXIST("404003", "Tag with id %d does not exist. "),
    COMMENT_ID_DOES_NOT_EXIST("404004", "Comment with id %d does not exist. "),
    VALIDATION_EXCEPTION("400001", "Validation failed");


    private final String errorMessage;
    private final String code;

    ServiceExceptionCode(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCode() {
        return code;
    }
}
