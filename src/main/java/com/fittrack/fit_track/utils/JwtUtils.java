package com.fittrack.fit_track.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    private Key signingKey;

    // Charger secret key depuis application.properties
    public JwtUtils(@Value("${jwt.secret}") String secretKey){
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        this.signingKey = Keys.hmacShaKeyFor(decodedKey);
    }

    public String generateJwtToken(Authentication authentication) {
        String email = authentication.getName();

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 86400000)) // 1 day validity
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserEmailFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Key getSigningKey(){
        return signingKey;
    }
}
