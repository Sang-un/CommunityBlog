package com.sanbon.backend.component.advice.handler;

import com.sanbon.backend.component.advice.ErrorResponse;
import com.sanbon.backend.component.advice.exception.EmailAlreadyExistException;
import com.sanbon.backend.component.advice.exception.PostNotFoundException;
import com.sanbon.backend.component.advice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        final ErrorResponse response = ErrorResponse.create()
                .status(e.getErrorCode().getStatus())
                .message(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleException(PostNotFoundException e) {
        final ErrorResponse response = ErrorResponse.create()
                .status(e.getErrorCode().getStatus())
                .message(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    protected ResponseEntity<ErrorResponse> handleException(EmailAlreadyExistException e) {
        final ErrorResponse response = ErrorResponse.create()
                .status(e.getErrorCode().getStatus())
                .code("Email is already existed.")
                .message(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }
        final ErrorResponse response = ErrorResponse.create()
                .status(400)
                .code(e.getParameter().toString())
                .message(builder.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
