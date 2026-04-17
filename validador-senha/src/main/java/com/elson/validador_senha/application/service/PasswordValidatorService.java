package com.elson.validador_senha.application.service;

import com.elson.validador_senha.domain.components.Interface.PasswordRule;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class PasswordValidatorService {

    private final List<PasswordRule> rules;

    public PasswordValidatorService(List<PasswordRule> rules) {
        this.rules = rules;
    }

    private static final Logger log = LoggerFactory.getLogger(PasswordValidatorService.class);

    public boolean validate(String password) {
        if (password == null) {
            log.error("Senha nula -> inválida");
            return false;
        }

        if (password.isBlank()) {
            log.error("Senha contém espaço em branco -> inválida");
            return false;
        }

        for (PasswordRule rule : rules) {
            if (!rule.isValid(password)) {
                 log.error("Falha na regra: {}", rule.getErrorMessage());
                 return false;
            }
        }
                return true;
    }
}


