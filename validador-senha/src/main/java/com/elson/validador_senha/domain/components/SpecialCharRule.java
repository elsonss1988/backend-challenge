package com.elson.validador_senha.domain.components;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;

import java.util.Set;

public class SpecialCharRule implements PasswordRule {
    private static final Set<Character> SPECIAL_CHARS = Set.of(
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'
    );

    @Override
    public boolean isValid(String password) {
        if (password == null) return false;
        return password.chars().anyMatch(c -> SPECIAL_CHARS.contains((char) c));
    }

    @Override
    public String getErrorMessage() {
        return "A senha deve conter pelo menos um caractere especial: !@#$%^&*()-+";
    }
}
