package com.elson.validador_senha.application.service;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PasswordValidatorService {

    private final List<PasswordRule> rules;

    public PasswordValidatorService(List<PasswordRule> rules) {
        this.rules = rules;
    }

    private static final Logger log = LoggerFactory.getLogger(PasswordValidatorService.class);

    public ValidationResult validate(String password) {
        if (password == null) {
            log.error("Senha nula -> inválida");
            return ValidationResult.failure("Password cannot be null");
        }

        if (password.isBlank()) {
            log.error("Senha contém espaço em branco -> inválida");
            return ValidationResult.failure("Password cannot contain whitespace");

        }

        for (PasswordRule rule : rules) {
            if (!rule.isValid(password)) {
                 log.error("Falha na regra: {}", rule.getErrorMessage());
                 return ValidationResult.failure(rule.getErrorMessage());
            }
        }
        return ValidationResult.success();
    }
}


