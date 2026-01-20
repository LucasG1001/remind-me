package com.example.remember_me.domain.validators;

public class NotNullValidator implements Validator<String> {

    private final String fieldName;

    public NotNullValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void validate(String value) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " não pode ser nulo");
        }
    }

}
