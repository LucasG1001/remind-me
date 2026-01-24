package com.example.remindme.infrastructure.user.mappers;

import java.util.stream.Collectors;

import com.example.remindme.domain.user.entities.User;
import com.example.remindme.domain.user.entities.UserCredentials;
import com.example.remindme.domain.user.valueObjects.Email;
import com.example.remindme.domain.user.valueObjects.UserName;
import com.example.remindme.infrastructure.user.entities.UserCredentialsEntity;
import com.example.remindme.infrastructure.user.entities.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        if (entity == null)
            return null;

        var domainCredentials = entity.getCredentials().stream()
                .map(UserMapper::toCredentialsDomain)
                .collect(Collectors.toList());

        return new User(
                entity.getId(),
                new UserName(entity.getUserName()), new Email(entity.getEmail()),
                entity.getRole(),
                domainCredentials);
    }

    private static UserCredentials toCredentialsDomain(UserCredentialsEntity entity) {
        return UserCredentials.reconstitute(
                entity.getId(),
                entity.getUser().getId(),
                entity.getProvider(),
                entity.getProviderId(),
                entity.getPasswordHash());
    }

    public static UserEntity toEntity(User user) {
        var entity = new UserEntity();
        entity.setUserName(user.getUserName().toString());
        entity.setEmail(user.getEmail().toString());
        entity.setRole(user.getRole());

        var credentialsEntities = user.getCredentials().stream()
                .map(UserMapper::toCredentialsEntity)
                .collect(Collectors.toList());

        entity.setCredentials(credentialsEntities);

        return entity;
    }

    private static UserCredentialsEntity toCredentialsEntity(UserCredentials credentials) {
        var entity = new UserCredentialsEntity();
        entity.setProvider(credentials.getProvider());
        entity.setProviderId(credentials.getProviderId());
        entity.setPasswordHash(credentials.getPasswordHash());
        return entity;
    }
}