package com.example.remindme.domain.user.repositories;

import java.util.Optional;

import com.example.remindme.domain.user.entities.User;

public interface UserRepository {
  Optional<User> findByEmail(String email);

  User save(User user);
}