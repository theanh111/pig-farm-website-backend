package com.school.core.response;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private HttpStatus errorStatus;

    public BaseException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.errorStatus = httpStatus;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }
}
