package com.example.remindme.infrastructure.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Value;

@Configuration
public class GoogleAuthConfig {

  @Value("${google.client-id}")
  private String clientId;

  @Bean
  public GoogleIdTokenVerifier googleIdTokenVerifier() {
    NetHttpTransport transport = new NetHttpTransport();

    GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    return new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(Collections.singletonList(clientId))
        .build();
  }

}
