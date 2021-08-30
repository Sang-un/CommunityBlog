package com.sanbon.backend.config.jwt;

public interface JwtProperties {
    String secret = "tripblog";
    long EXPIRATION_TIME = 600000L * 10; // 10분 // 10일 (1/1000초) 864000000
    long REFRESH_EXPIRATION_TIME = 600000L * 60 * 24 * 10; // 10일
    String TOKEN_PREFIX = "Bearer ";
    String ACCESS_HEADER_STRING = "Authorization";
    String REFRESH_HEADER_STRING = "Refresh_Token";
}
