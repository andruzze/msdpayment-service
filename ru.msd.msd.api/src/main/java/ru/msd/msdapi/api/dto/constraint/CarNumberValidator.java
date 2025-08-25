package ru.msd.msdapi.api.dto.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CarNumberValidator implements ConstraintValidator<ValidCarNumber, String> {

    private static final Pattern RUSSIAN_CAR_NUMBER_PATTERN = Pattern.compile(
            "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return false;
        String normalized = value.trim().toUpperCase();
        return RUSSIAN_CAR_NUMBER_PATTERN.matcher(normalized).matches();
    }
}
