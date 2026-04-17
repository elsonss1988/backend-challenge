package com.elson.validador_senha.infrastructure.api;

import com.elson.validador_senha.application.dto.ValidationRequest;
import com.elson.validador_senha.application.dto.ValidationResponse;
import com.elson.validador_senha.application.service.PasswordValidatorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {

    private final PasswordValidatorService validator;

    public PasswordController(PasswordValidatorService validator) {
        this.validator = validator;
    }

    @PostMapping("validate")
    public ResponseEntity<ValidationResponse> validate(@RequestBody @Valid ValidationRequest request) {

        ValidationResponse result = validator.validate(request.password());
        return ResponseEntity.ok(result);
    }
}
