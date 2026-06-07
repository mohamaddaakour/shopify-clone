package com.shopify.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

// to use the @value
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

@Service
public class JwtService {

    // we get the value of the secret key from .env file
    @Value("${jwt.secret}")
    private String secret;

    // the expiration duration is one day in milleseconds 
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    // we convert the secret key from a regular string into a cryptographic
    // key and can be handled with jwt
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // function to generate a token
    public String generateToken(String email) {

        // the subject is the payload of the token
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }

    // to extract the payload (email here) from the token
    public String extractEmail(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // to check if the token is correct
    public boolean isValid(String token) {

        try {

            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}