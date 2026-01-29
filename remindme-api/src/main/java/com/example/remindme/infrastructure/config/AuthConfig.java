package com.example.remindme.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.remindme.application.auth.gateway.GoogleAuthGateway;
import com.example.remindme.application.auth.usecases.LoginWithGoogleUseCase;
import com.example.remindme.application.shared.security.TokenProvider;
import com.example.remindme.domain.user.repositories.UserRepository;

@Configuration
public class AuthConfig {

  @Bean
  public LoginWithGoogleUseCase loginWithGoogleUseCase(GoogleAuthGateway googleAuthGateway,
      UserRepository userRepository, TokenProvider tokenProvider) {
    return new LoginWithGoogleUseCase(googleAuthGateway, userRepository, tokenProvider);
  }

}
