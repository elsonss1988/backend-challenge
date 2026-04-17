package com.elson.validador_senha.application.dto;

public record ValidationResponse(boolean valid, String message ) {
    public static ValidationResponse success() {
        return new ValidationResponse(true, null);
    }

    public static ValidationResponse failure(String message) {
        return new ValidationResponse(false, message);
    }

    public boolean isValid() { return valid; }
    public String getErrorMessage() { return message; }
}
