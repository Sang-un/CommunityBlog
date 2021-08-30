package com.sanbon.backend.component.advice.exception;

import com.sanbon.backend.component.advice.ErrorCode;
import lombok.Getter;

@Getter
public class EmailAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public EmailAlreadyExistException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
