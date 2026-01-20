package com.example.remember_me.domain.validators;

import java.util.List;

public class ValidationComposite<T> implements Validator<T> {

    private final List<Validator<T>> validators;

    private ValidationComposite(List<Validator<T>> validators) {
        this.validators = validators;
    }

    @SafeVarargs
    public static <T> ValidationComposite<T> of(Validator<T>... validators) {
        return new ValidationComposite<>(List.of(validators));
    }

    @Override
    public void validate(T value) {
        for (Validator<T> validator : validators) {
            validator.validate(value);
        }
    }

}
