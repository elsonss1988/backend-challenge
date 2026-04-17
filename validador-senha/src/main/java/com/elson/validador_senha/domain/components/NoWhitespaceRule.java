package com.elson.validador_senha.domain.components;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;
import org.springframework.stereotype.Component;

@Component
public class NoWhitespaceRule implements PasswordRule {
    @Override
    public boolean isValid(String password) {
        return password != null && !containsWhitespace(password);
    }

    private boolean containsWhitespace(String str) {
        return str.chars().anyMatch(Character::isWhitespace);
    }

    @Override
    public String getErrorMessage() {
        return "A senha não pode conter espaços em branco";
    }
}