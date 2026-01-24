package com.example.remindme.infrastructure.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.remindme.application.shared.security.TokenProvider;
import com.example.remindme.domain.user.entities.User;
import com.example.remindme.shared.utils.DateUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider implements TokenProvider {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration-ms}")
  private int jwtExpirationMs;

  @Value("${jwt.refresh-expiration-ms}")
  private int jwtRefreshExpirationMs;

  @Value("${jwt.confirmation-expiration-ms}")
  private int jwtConfirmationTokenExpirationMs;

  @Value("${jwt.secret}")
  private String secretkey;

  private SecretKey key() {
    String secretString = secretkey;
    return new SecretKeySpec(secretString.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
  }

  public String generateAccessToken(User user) {
    return tokenBuilder(user, DateUtil.addMillisecondsToNow(jwtExpirationMs));
  }

  public String generateConfirmationToken(User user) {
    return tokenBuilder(user, DateUtil.addMillisecondsToNow(jwtConfirmationTokenExpirationMs));
  }

  public String generateRefreshToken(User user) {
    return tokenBuilder(user, DateUtil.addMillisecondsToNow(jwtRefreshExpirationMs));
  }

  public String getUsernameFromToken(String token) {
    Claims claims = Jwts.parser()
        .verifyWith(key())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    return claims.getSubject();
  }

  public void validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(key())
          .build()
          .parseSignedClaims(token);
    } catch (ExpiredJwtException ex) {
      throw new IllegalArgumentException("Token expirado");
    } catch (JwtException | IllegalArgumentException ex) {
      throw new IllegalArgumentException("Token inválido");
    }
  }

  private String tokenBuilder(User user, Date expiresAt) {
    return Jwts.builder()
        .subject(user.getEmail().toString())
        .issuedAt(new Date())
        .expiration(expiresAt)
        .signWith(key())
        .compact();
  }
}