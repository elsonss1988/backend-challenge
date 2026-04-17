package com.elson.validador_senha.domain.components;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;
import org.springframework.stereotype.Component;

@Component
public class DigitRule implements PasswordRule {
    @Override
    public boolean isValid(String password) {
        if (password == null) return false;
        return password.chars().anyMatch(Character::isDigit);
    }

    @Override
    public String getErrorMessage() {
        return "A senha deve conter pelo menos um dígito (0-9)";
    }
}
