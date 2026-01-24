package com.example.remindme.infrastructure.user.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.remindme.domain.user.entities.User;
import com.example.remindme.domain.user.repositories.UserRepository;
import com.example.remindme.infrastructure.user.entities.UserEntity;
import com.example.remindme.infrastructure.user.mappers.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SpringDataUserRepository jpaRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity userEntityCreated = jpaRepository.save(userEntity);
        return UserMapper.toDomain(userEntityCreated);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserMapper::toDomain);
    }

}