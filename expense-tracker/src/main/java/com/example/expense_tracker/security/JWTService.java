package com.example.expense_tracker.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.expense_tracker.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private long expiration;


  public String generateToken(User user) {
    return Jwts.builder()
            .subject(user.getEmail())
            .issuedAt(new Date())
            .expiration(
                    new Date(
                            System.currentTimeMillis() + expiration
                    )
            )
            .signWith(getSigningKey())
            .compact();
}

public String extractUsername(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
}

public Date extractExpiration(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration();
}

public boolean isTokenValid(String token, User user) {

    String username = extractUsername(token);

    return username.equals(user.getEmail())
            && !extractExpiration(token)
                    .before(new Date());
}

  private SecretKey getSigningKey() {

    byte[] keyBytes =
            Decoders.BASE64.decode(secretKey);

    return Keys.hmacShaKeyFor(keyBytes);
}
  
}
