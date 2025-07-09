package com.potodev.NapRoute.exceptions;

import org.springframework.http.HttpStatus;

public class NapException extends RuntimeException {

    private final HttpStatus httpStatus;

    public NapException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // default fallback
    }

    public NapException(HttpStatus status, String message) {
        super(message);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static NapException of(NapErrorCode napErrorCode) {
        return new NapException(napErrorCode.getHttpStatus(), napErrorCode.getMessage());
    }
}
