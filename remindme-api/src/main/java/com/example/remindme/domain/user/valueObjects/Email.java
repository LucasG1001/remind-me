package com.example.remindme.domain.user.valueObjects;

import com.example.remindme.domain.validators.MatchesValidator;
import com.example.remindme.domain.validators.MaxLengthValidator;
import com.example.remindme.domain.validators.NotNullValidator;
import com.example.remindme.domain.validators.ValidationComposite;
import com.example.remindme.domain.validators.Validator;

public record Email(String value) {
    private static final String FIELD_NAME = "Email";
    private static final Validator<String> VALIDATOR = ValidationComposite.of(
            new NotNullValidator(FIELD_NAME),
            new MaxLengthValidator(150, FIELD_NAME),
            new MatchesValidator(FIELD_NAME, "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));

    public Email {
        VALIDATOR.validate(value);
        value = value.toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }
}