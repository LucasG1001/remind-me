package com.example.remindme.infrastructure.user.mappers;

import com.example.remindme.domain.user.entities.User;
import com.example.remindme.domain.user.entities.UserCredentials;
import com.example.remindme.domain.user.valueObjects.Email;
import com.example.remindme.domain.user.valueObjects.UserName;
import com.example.remindme.infrastructure.user.UserCredentialsEntity;
import com.example.remindme.infrastructure.user.UserEntity;

import java.util.stream.Collectors;

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
        var entity = new UserEntity(user.getId(), user.getUserName().toString(), user.getEmail().toString(),
                user.getRole());
        return entity;
    }

    private static UserCredentialsEntity toEntity(User user, UserCredentials credentials) {
        return new UserCredentialsEntity();
    }
}