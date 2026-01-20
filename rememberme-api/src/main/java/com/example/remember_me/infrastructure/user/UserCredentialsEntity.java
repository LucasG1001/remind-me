package com.example.remember_me.infrastructure.user;

import com.example.remember_me.domain.user.enums.AuthProvider;
import com.example.remember_me.infrastructure.shared.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_credentials")
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private AuthProvider provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "password_hash")
    private String passwordHash;
}