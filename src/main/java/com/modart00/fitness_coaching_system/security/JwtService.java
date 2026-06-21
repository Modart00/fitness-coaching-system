package com.modart00.fitness_coaching_system.security;

import com.modart00.fitness_coaching_system.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService{

    @Value("${JWT_SECRET}")
    private final String secretKey;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user){
        SecretKey secretKey = getSigningKey();

        return Jwts.builder()
                .subject(user
                        .getEmail()).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(secretKey)
                .compact();

    }

    public Claims extractAllClaims(String token){
        SecretKey secretKey = getSigningKey();

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        Date currentDate = new Date(System.currentTimeMillis());
        Date expirationDate = extractExpiration(token);

        if (currentDate.after(expirationDate)){
            return true; } else return false;

    }

    public boolean validateToken(String token,User user){
        String email = extractEmail(token);
        if (isTokenExpired(token) || !email.equals(user.getEmail())){
            return false;
        } else return true;
    }

}
