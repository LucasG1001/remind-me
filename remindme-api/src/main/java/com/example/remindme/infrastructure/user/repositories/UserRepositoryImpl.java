package com.example.remindme.infrastructure.user.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.remindme.domain.user.entities.User;
import com.example.remindme.infrastructure.user.UserEntity;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private SpringDataUserRepository jpaRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(UserMapper::toDomain);

    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity userEntityCreated = jpaRepository.save(userEntity);
        return userMapper.toDomain(userEntityCreated);
    }

}