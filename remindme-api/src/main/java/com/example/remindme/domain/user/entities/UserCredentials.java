package com.example.remindme.domain.user.entities;

import com.example.remindme.domain.user.enums.AuthProvider;
import java.util.Objects;
import java.util.UUID;

public final class UserCredentials {

    private final UUID id;
    private final UUID userId;
    private final AuthProvider provider;
    private final String providerId;
    private final String passwordHash;

    private UserCredentials(UUID id, UUID userId, AuthProvider provider, String providerId, String passwordHash) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.provider = Objects.requireNonNull(provider);
        this.providerId = providerId;
        this.passwordHash = passwordHash;
    }

    public static UserCredentials createSocial(UUID userId, AuthProvider provider, String providerId) {
        if (provider == AuthProvider.LOCAL)
            throw new IllegalArgumentException("Provider must be social");

        return new UserCredentials(UUID.randomUUID(), userId, provider, providerId, null);
    }

    public static UserCredentials reconstitute(UUID id, UUID userId, AuthProvider provider, String providerId,
            String passwordHash) {
        return new UserCredentials(id, userId, provider, providerId, passwordHash);
    }

    public static UserCredentials createLocal(UUID userId, String passwordHash) {
        if (passwordHash == null)
            throw new IllegalArgumentException("PasswordHash must not be null");
        return new UserCredentials(UUID.randomUUID(), userId, AuthProvider.LOCAL, null, passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
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