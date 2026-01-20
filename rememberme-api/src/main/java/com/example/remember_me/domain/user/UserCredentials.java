package com.example.remember_me.domain.user;

import com.example.remember_me.domain.user.enums.AuthProvider;

public class UserCredentials {
    private User user;
    private AuthProvider provider;
    private String providerId;
    private String passwordHash;

    protected UserCredentials() {
    }

    public static UserCredentials local(User user, String passwordHash) {
        if (user == null || passwordHash == null) {
            throw new IllegalArgumentException("User e passwordHash não podem ser nulos.");
        }
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.user = user;
        userCredentials.passwordHash = passwordHash;
        return userCredentials;
    }

    public static UserCredentials oauth(User user, AuthProvider provider, String providerId) {
        if (user == null || provider == null || providerId == null) {
            throw new IllegalArgumentException("Dados de OAuth incompletos.");
        }
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.user = user;
        userCredentials.provider = provider;
        userCredentials.providerId = providerId;
        return userCredentials;
    }

    public User getUser() {
        return user;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}
