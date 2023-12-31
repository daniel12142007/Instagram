package com.example.instagram.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

// TODO: 4
@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username) {
        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("daniel_tamoe")
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMonths(1).toInstant()))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("daniel_tamoe")
                .build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim("username").asString();
    }
}