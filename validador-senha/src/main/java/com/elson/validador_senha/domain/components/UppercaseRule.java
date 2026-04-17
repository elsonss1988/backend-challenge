package com.elson.validador_senha.domain.components;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;

public class UppercaseRule implements PasswordRule {
    @Override
    public boolean isValid(String password) {
        if (password == null) return false;
        return password.chars().anyMatch(Character::isUpperCase);
    }

    @Override
    public String getErrorMessage() {
        return "A senha deve conter pelo menos uma letra maiúscula (A-Z)";
    }
}
