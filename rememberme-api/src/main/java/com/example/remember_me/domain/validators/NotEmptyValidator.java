package com.example.remember_me.domain.validators;

public class NotEmptyValidator implements Validator<String> {
    private final String fieldName;

    public NotEmptyValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " não pode ser vazio");
        }
    }
}