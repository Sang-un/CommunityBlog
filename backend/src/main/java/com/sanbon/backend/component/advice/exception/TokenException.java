package com.sanbon.backend.component.advice.exception;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.sanbon.backend.component.advice.ErrorCode;
import lombok.Getter;

@Getter
public class TokenException extends InvalidClaimException {
    private ErrorCode errorCode;

    public TokenException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
