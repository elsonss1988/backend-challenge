package com.elson.validador_senha.application.service;

import com.elson.validador_senha.application.dto.ValidationResponse;
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

    public ValidationResponse validate(String password) {
        if (password == null) {
            log.error("Senha nula -> inválida");
            return ValidationResponse .failure("A senha não pode ser nula");
        }

        if (password.isBlank()) {
            log.error("Senha contém espaço em branco -> inválida");
            return ValidationResponse .failure("Password cannot contain whitespace");

        }

        for (PasswordRule rule : rules) {
            if (!rule.isValid(password)) {
                 log.error("Falha na regra: {}", rule.getErrorMessage());
                 return ValidationResponse .failure(rule.getErrorMessage());
            }
        }
        return ValidationResponse .success();
    }
}


