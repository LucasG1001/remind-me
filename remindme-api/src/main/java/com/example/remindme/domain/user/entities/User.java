package com.example.remindme.domain.user.entities;

import java.util.List;
import java.util.UUID;

import com.example.remindme.domain.user.enums.AuthProvider;
import com.example.remindme.domain.user.enums.UserRole;
import com.example.remindme.domain.user.valueObjects.Email;
import com.example.remindme.domain.user.valueObjects.UserName;

public class User {
    private final UUID id;
    private final UserName userName;
    private final Email email;
    private final UserRole userRole;
    private List<UserCredentials> credentials;

    public User(UUID id, UserName userName, Email email, UserRole userRole, List<UserCredentials> credentials) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.userRole = userRole;
        this.credentials = List.copyOf(credentials);
    }

    public UUID getId() {
        return id;
    }

    public UserName getUserName() {
        return userName;
    }

    public Email getEmail() {
        return email;
    }

    public UserRole getRole() {
        return userRole;
    }

    public List<UserCredentials> getCredentials() {
        return credentials;
    }

    public boolean isRegisteredWith(AuthProvider provider) {
        return credentials.stream().anyMatch(credentials -> credentials.getProvider() == provider);
    }
}