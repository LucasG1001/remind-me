package com.example.remindme.infrastructure.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.remindme.infrastructure.user.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Component
public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}