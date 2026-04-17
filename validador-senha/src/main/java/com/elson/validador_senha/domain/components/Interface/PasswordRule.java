package com.elson.validador_senha.domain.components.Interface;

public interface PasswordRule {
    boolean isValid(String password);
    String getErrorMessage();
}