package com.example.book.service.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!()_.,:;?\\-])[A-Za-z0-9@#$%^&+=!()_.,:;?\\-]{8,}$";

    private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && pattern.matcher(password).matches();
    }
}

