package com.example.remindme.domain.validators;

public interface Validator<T> {
    void validate(T value);
}
