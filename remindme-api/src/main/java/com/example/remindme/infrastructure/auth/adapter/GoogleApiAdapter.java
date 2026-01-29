package com.example.remindme.infrastructure.auth.adapter;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.remindme.application.auth.dtos.GoogleUserDTO;
import com.example.remindme.application.auth.gateway.GoogleAuthGateway;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

@Component
public class GoogleApiAdapter implements GoogleAuthGateway {

  private final GoogleIdTokenVerifier verifier;

  public GoogleApiAdapter(GoogleIdTokenVerifier verifier) {
    this.verifier = verifier;
  }

  @Override
  public Optional<GoogleUserDTO> verifyToken(String token) {
    try {
      GoogleIdToken idToken = verifier.verify(token);
      if (idToken != null) {
        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String username = (String) payload.get("name");
        String id = (String) payload.get("sub");
        return Optional.of(new GoogleUserDTO(id, username, email));
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return Optional.empty();

  }

}