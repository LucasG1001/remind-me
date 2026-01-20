package com.example.remember_me.domain.valueObjects.User;

import com.example.remember_me.domain.validators.BetweenLengthValidator;
import com.example.remember_me.domain.validators.MatchesValidator;
import com.example.remember_me.domain.validators.NotEmptyValidator;
import com.example.remember_me.domain.validators.ValidationComposite;
import com.example.remember_me.domain.validators.Validator;

public record UserName(String value) {

    private static final String FIELD_NAME = "UserName";
    private static final Validator<String> VALIDATOR = ValidationComposite.of(
            new NotEmptyValidator(FIELD_NAME),
            new BetweenLengthValidator(FIELD_NAME, 3, 30),
            new MatchesValidator(FIELD_NAME, "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));

    public UserName {
        VALIDATOR.validate(value);
        value = value.trim();
    }

    @Override
    public String toString() {
        return value;
    }
}