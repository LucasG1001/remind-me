package com.example.remember_me.domain.user;

import com.example.remember_me.domain.user.enums.UserRole;
import com.example.remember_me.domain.user.valueObjects.Email;
import com.example.remember_me.domain.user.valueObjects.UserName;

public class User {
    private final UserName userName;
    private final Email email;
    private final UserRole userRole;
    private UserCredentials credentials;

    public User(UserName userName, Email email, UserRole userRole, UserCredentials credentials) {
        this.userName = userName;
        this.email = email;
        this.userRole = userRole;
        this.credentials = credentials;
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

    public UserCredentials getCredentials() {
        return credentials;
    }
}