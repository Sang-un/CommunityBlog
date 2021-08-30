package com.sanbon.backend.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanbon.backend.component.utils.JwtUtil;
import com.sanbon.backend.component.utils.RedisUtil;
import com.sanbon.backend.model.dto.request.user.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();
        LoginRequestDto loginRequestDto = null;
        try {
            loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        // 수정해야되는 부분
        // session 을 사용하기 때문에
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String jwtToken = jwtUtil.createToken(authResult, JwtProperties.EXPIRATION_TIME);
        String jwtRefreshToken = jwtUtil.createToken(authResult, JwtProperties.REFRESH_EXPIRATION_TIME);
        response.addHeader(JwtProperties.ACCESS_HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
        response.addHeader(JwtProperties.REFRESH_HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtRefreshToken);
        redisUtil.setDataExpire(authResult.getName(), jwtRefreshToken, JwtProperties.REFRESH_EXPIRATION_TIME);
    }
}
