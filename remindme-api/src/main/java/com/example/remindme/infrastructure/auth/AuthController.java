package com.example.remindme.infrastructure.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.remindme.application.auth.dtos.GoogleLoginDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @PostMapping("/google")
  public String loginWithGoogle(@RequestBody @Valid GoogleLoginDTO googleLoginDTO) {
    return "google";
  }

}
