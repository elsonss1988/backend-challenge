package com.elson.validador_senha.domain.components;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class NoRepeatedCharRule implements PasswordRule {
    @Override
    public boolean isValid(String password) {
        if (password == null) return false;
        Set<Character> seen = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (!seen.add(c)) return false;
        }
        return true;
    }
    @Override
    public String getErrorMessage() {
        return "A senha não pode conter caracteres repetidos";
    }
}
