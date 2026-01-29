package com.example.remindme.application.shared.security;

import com.example.remindme.domain.user.entities.User;

public interface TokenProvider {
  public String generateAccessToken(User user);

  public void validateToken(String token);

  public String getUsernameFromToken(String token);
}