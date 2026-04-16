package com.elson.validador_senha.infrastructure.api;

import com.elson.validador_senha.application.service.ValidadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {

    @PostMapping("validate")
    public ResponseEntity<String> validate(@RequestParam("password") String password) {
            return ResponseEntity.ok("sucess");
    }

    @GetMapping("validate")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("sucess");
    }
}


