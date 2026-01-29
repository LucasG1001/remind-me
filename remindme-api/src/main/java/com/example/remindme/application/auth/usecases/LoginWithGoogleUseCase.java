package com.example.remindme.application.auth.usecases;

import com.example.remindme.application.auth.dtos.GoogleLoginDTO;
import com.example.remindme.application.auth.dtos.GoogleUserDTO;
import com.example.remindme.application.auth.gateway.GoogleAuthGateway;
import com.example.remindme.application.shared.dto.LoginResponseDTO;
import com.example.remindme.application.shared.security.TokenProvider;
import com.example.remindme.domain.user.entities.User;
import com.example.remindme.domain.user.entities.UserCredentials;
import com.example.remindme.domain.user.enums.AuthProvider;
import com.example.remindme.domain.user.enums.UserRole;
import com.example.remindme.domain.user.repositories.UserRepository;
import com.example.remindme.domain.user.valueObjects.UserName;
import com.example.remindme.domain.user.valueObjects.Email;

import java.util.Collections;

public class LoginWithGoogleUseCase {

  private final GoogleAuthGateway googleAuthGateway;
  private final UserRepository userRepository;
  private final TokenProvider tokenProvider;

  public LoginWithGoogleUseCase(GoogleAuthGateway googleAuthGateway, UserRepository userRepository,
      TokenProvider tokenProvider) {
    this.googleAuthGateway = googleAuthGateway;
    this.userRepository = userRepository;
    this.tokenProvider = tokenProvider;
  }

  public LoginResponseDTO execute(GoogleLoginDTO googleLoginDTO) {
    GoogleUserDTO googleUser = googleAuthGateway.verifyToken(googleLoginDTO.token())
        .orElseThrow(() -> new IllegalArgumentException("Invalid Google Token"));

    User user = userRepository.findByEmail(googleUser.email())
        .orElseGet(() -> createNewGoogleUser(googleUser));

    String token = tokenProvider.generateAccessToken(user);

    return new LoginResponseDTO(token, user.getEmail().toString());
  }

  private User createNewGoogleUser(GoogleUserDTO googleUser) {
    UserCredentials credentials = UserCredentials.createSocial(
        null,
        AuthProvider.GOOGLE,
        googleUser.id());

    User newUser = new User(
        null,
        new UserName(googleUser.username()),
        new Email(googleUser.email()),
        UserRole.USER,
        Collections.singletonList(credentials));

    return userRepository.save(newUser);
  }
}