package com.sanbon.backend.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sanbon.backend.component.advice.ErrorCode;
import com.sanbon.backend.component.advice.exception.TokenException;
import com.sanbon.backend.component.utils.JwtUtil;
import com.sanbon.backend.component.utils.RedisUtil;
import com.sanbon.backend.config.auth.PrincipalDetails;
import com.sanbon.backend.model.user.User;
import com.sanbon.backend.repository.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RedisUtil redisUtil;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, RedisUtil redisUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.ACCESS_HEADER_STRING);
        // token 없거나 prefix 잘못된 경우
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String accessToken = header.replace(JwtProperties.TOKEN_PREFIX, "");
        String refreshToken = null;
        try {
            DecodedJWT decodedJWT = jwtUtil.decodedJWT(accessToken);
            if (jwtUtil.isValid(decodedJWT)) {
                String userEmail = decodedJWT.getClaim("email").asString();
                User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("user email not found"));
                PrincipalDetails principalDetails = new PrincipalDetails(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (TokenExpiredException e) {
            header = request.getHeader(JwtProperties.REFRESH_HEADER_STRING);
            if (header == null) {
                throw new TokenException("access token", ErrorCode.TOKEN_EXPIRED);
            }
            refreshToken = header.replace(JwtProperties.TOKEN_PREFIX, "");
        } catch (JWTVerificationException e) {
            throw new TokenException("invalid token", ErrorCode.INVALID_TOKEN);
        }

        try {
            if (refreshToken != null) {
                String userEmail = jwtUtil.decodedJWT(refreshToken).getClaim("email").asString();
                String redisRefreshToken = redisUtil.getData(userEmail);

                if (redisRefreshToken.equals(refreshToken)) {
                    User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("user name not found"));
                    PrincipalDetails principalDetails = new PrincipalDetails(user);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String jwtToken = jwtUtil.createToken(authentication, JwtProperties.EXPIRATION_TIME);
                    response.addHeader(JwtProperties.ACCESS_HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
                }
            }

        } catch (TokenExpiredException e) {
            throw new TokenException("refresh token", ErrorCode.TOKEN_EXPIRED);
        } catch (JWTVerificationException e) {
            throw new TokenException("invalid token", ErrorCode.INVALID_TOKEN);
        }
        chain.doFilter(request, response);


    }
}
