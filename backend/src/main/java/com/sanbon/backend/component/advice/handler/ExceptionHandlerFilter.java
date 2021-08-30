package com.sanbon.backend.component.advice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanbon.backend.component.advice.ErrorCode;
import com.sanbon.backend.component.advice.ErrorResponse;
import com.sanbon.backend.component.advice.exception.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenException ex) {
            ErrorResponse errorResponse = null;
            ErrorCode errorCode = ex.getErrorCode();
            if (ex.getMessage().equals("access token")) {
                errorResponse = new ErrorResponse(errorCode.getMessage(), "Access Token Expired", errorCode.getStatus());
            } else if (ex.getMessage().equals("refresh token")) {
                errorResponse = new ErrorResponse(errorCode.getMessage(), "Refresh Token Expired", errorCode.getStatus());
            } else if (ex.getMessage().equals("invalid token")) {
                errorResponse = new ErrorResponse(errorCode.getMessage(), "Invalid Token", errorCode.getStatus());
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
        }
    }

    private String convertObjectToJson(ErrorResponse errorResponse) throws JsonProcessingException {
        if (errorResponse == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);

    }
}
