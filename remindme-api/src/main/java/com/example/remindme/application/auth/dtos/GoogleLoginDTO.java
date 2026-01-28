package com.example.remindme.application.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record GoogleLoginDTO(
                @NotBlank(message = "O token não pode estar vazio") String token) {
}