package com.elson.validador_senha.application.dto;


import jakarta.validation.constraints.NotNull;

public record ValidationRequest(
        @NotNull(message = "Password cannot be null")
        String password
) {}
