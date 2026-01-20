package com.example.remember_me.domain.validators;

public interface Validator<T> {
    void validate(T value);
}
