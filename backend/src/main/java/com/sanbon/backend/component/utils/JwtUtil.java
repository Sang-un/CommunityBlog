package com.sanbon.backend.component.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sanbon.backend.config.auth.PrincipalDetails;
import com.sanbon.backend.config.jwt.JwtProperties;
import com.sanbon.backend.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    public String createToken(Authentication authentication, long expiredTime) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        System.out.println(user.getRoleKey());

        return JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiredTime))
                .withClaim("role", user.getRoleKey())
                .withClaim("name", user.getName())
                .withClaim("email", user.getEmail())
                .sign(Algorithm.HMAC256(JwtProperties.secret));
    }


    public DecodedJWT decodedJWT(String jwt) throws TokenExpiredException, InvalidClaimException {
        return JWT.require(Algorithm.HMAC256(JwtProperties.secret)).build().verify(jwt);
    }

    public boolean isValid(DecodedJWT decodedJWT) throws TokenExpiredException, InvalidClaimException {
        return decodedJWT.getExpiresAt().after(new Date());
    }
}
