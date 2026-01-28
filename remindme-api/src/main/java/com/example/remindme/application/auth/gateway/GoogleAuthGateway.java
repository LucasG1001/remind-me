package com.example.remindme.application.auth.gateway;

import java.util.Optional;

import com.example.remindme.application.auth.dtos.GoogleUserDTO;

public interface GoogleAuthGateway {
  Optional<GoogleUserDTO> verifyToken(String token);
}