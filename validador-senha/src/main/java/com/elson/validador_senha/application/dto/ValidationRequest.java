package com.elson.validador_senha.application.dto;


import jakarta.validation.constraints.NotNull;

public record ValidationRequest(
        @NotNull(message = "A senha não pode ser nula")
        String password
) {}
