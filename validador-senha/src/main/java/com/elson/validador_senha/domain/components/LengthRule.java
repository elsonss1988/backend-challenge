package com.elson.validador_senha.domain.components;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;
import org.springframework.stereotype.Component;

@Component
public class LengthRule implements PasswordRule {
    private static final int MIN_LENGTH = 9;
    @Override
    public boolean isValid(String password) {
        return password != null && password.length() >= MIN_LENGTH;
    }
    @Override
    public String getErrorMessage() {
        return "A senha deve ter pelo menos " + MIN_LENGTH + " caracteres";
    }
}