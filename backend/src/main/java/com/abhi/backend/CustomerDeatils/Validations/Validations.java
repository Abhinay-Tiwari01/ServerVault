package com.abhi.backend.CustomerDeatils.Validations;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Service
public final class Validations {
    private Validations() {};

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$");

    public static boolean isValidPassword(String password) {
        return password != null && !password.isEmpty() && PASSWORD_PATTERN.matcher(password).matches();
    }
}
