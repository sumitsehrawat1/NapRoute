package com.potodev.NapRoute.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum NapErrorCode {
    BAD_CREDENTIALS(HttpStatus.BAD_REQUEST, "Invalid credentials"),
    USERNAME_ALREADY_TAKEN(HttpStatus.BAD_REQUEST, "Username already taken"),
    USERNAME_NOT_FOUND(HttpStatus.BAD_REQUEST, "User not found"),
    ACTIVE_GEO_TAG_PRESENT(HttpStatus.BAD_REQUEST, "active geo tag present"),

    USERTAG_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "UserTag not found"),
    GEOTAG_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "GeoTag not found"),
    ;

    @Getter private final HttpStatus httpStatus;
    @Getter private final String message;
    // todo: debugId and reference should be added

    NapErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
