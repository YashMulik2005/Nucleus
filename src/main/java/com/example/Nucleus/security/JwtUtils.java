package com.example.Nucleus.security;

import com.example.Nucleus.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.accessTokenKey}")
    private String accessTokenKey;

    @Value("${jwt.refreshTokenKey}")
    private String refreshTokenKey;

    private SecretKey getAccessTokenKey(){
        return Keys.hmacShaKeyFor(accessTokenKey.getBytes(StandardCharsets.UTF_8));
    }

    private SecretKey getRefreshTokenKey(){
        return Keys.hmacShaKeyFor(refreshTokenKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("UserId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getAccessTokenKey())
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("UserId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10))
                //1000 * 60 = 1 min  1000 = 1 sec
                .signWith(getRefreshTokenKey())
                .compact();
    }

    public String getUsernameFromAccessToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getAccessTokenKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public String getUsernameFromRefreshToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getRefreshTokenKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
