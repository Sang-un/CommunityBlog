package com.sanbon.backend.component.advice;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_PARAMETER(400, null, "Invalid Request Data"),
    TOKEN_EXPIRED(400, null, "Access Token Expired"),
    INVALID_TOKEN(400, null, "Invalid Token");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}

