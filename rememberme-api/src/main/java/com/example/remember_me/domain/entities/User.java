package com.example.remember_me.domain.entities;

import com.example.remember_me.domain.valueObjects.User.Email;
import com.example.remember_me.domain.valueObjects.User.UserName;

public class User {
    private final UserName userName;
    private final Email email;

    public User(UserName userName, Email email) {
        this.userName = userName;
        this.email = email;
    }

    public UserName getUserName() {
        return userName;
    }

    public Email getEmail() {
        return email;
    }
}