package com.example.remindme.application.auth.usecases;

import java.util.List;

import com.example.remindme.application.auth.dtos.GoogleLoginDTO;
import com.example.remindme.application.auth.dtos.GoogleUserDTO;
import com.example.remindme.application.auth.gateway.GoogleAuthGateway;
import com.example.remindme.application.shared.dto.LoginResponseDTO;
import com.example.remindme.domain.user.entities.User;
import com.example.remindme.domain.user.entities.UserCredentials;
import com.example.remindme.domain.user.enums.UserRole;
import com.example.remindme.domain.user.repositories.UserRepository;

public class LoginWithGoogleUseCase {

  private final GoogleAuthGateway googleAuthGateway;
  private final UserRepository userRepository;

  public LoginWithGoogleUseCase(GoogleAuthGateway googleAuthGateway, UserRepository userRepository) {
    this.googleAuthGateway = googleAuthGateway;
    this.userRepository = userRepository;
  }

  public LoginResponseDTO execute(GoogleLoginDTO googleLoginDTO) {
    GoogleUserDTO googleUser = googleAuthGateway.verifyToken(googleLoginDTO.token())
        .orElseThrow(() -> new RuntimeException("Token do Google inválido."));

        List<UserCredentials> credentials = new ArrayList<>();
        credentials.add(UserCredentials.reconstitute(null, null, null, null, null));
        
var user = userRepository.findByEmail(googleUser.email())
        .orElseGet(() -> userRepository.save(new User(null, googleUser.username(), googleUser.email(), UserRole.USER, new List<UserCr )));

    LoginResponseDTO loginResponseDTO = new LoginResponseDTO("DFSDFSDFSD", user.getEmail().toString());
    return loginResponseDTO;

  }

}
