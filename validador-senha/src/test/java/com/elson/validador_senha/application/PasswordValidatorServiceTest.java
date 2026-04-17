package com.elson.validador_senha.application;

import com.elson.validador_senha.application.dto.ValidationResponse;
import com.elson.validador_senha.application.service.PasswordValidatorService;
import com.elson.validador_senha.domain.components.*;
import com.elson.validador_senha.domain.components.Interface.PasswordRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorServiceTest {

    private PasswordValidatorService service;

    @Mock
    private LengthRule lengthRule;
    @Mock
    private DigitRule digitRule;

    @BeforeEach
    void setUp() {
        service = new PasswordValidatorService(List.of(lengthRule, digitRule));
    }

    @Test
    void shouldReturnSuccessWhenAllRulesPass() {
        when(lengthRule.isValid("valid")).thenReturn(true);
        when(digitRule.isValid("valid")).thenReturn(true);

        ValidationResponse response = service.validate("valid");

        assertTrue(response.valid());
        assertEquals("sua senha é valida", response.message());
    }

    @Test
    void shouldReturnFailureWhenFirstRuleFails() {
        when(lengthRule.isValid("fail")).thenReturn(false);
        when(lengthRule.getErrorMessage()).thenReturn("A senha deve ter pelo menos 9 caracteres");

        ValidationResponse response = service.validate("fail");

        assertFalse(response.valid());
        assertEquals("A senha deve ter pelo menos 9 caracteres", response.message());
    }

    @Test
    void shouldReturnFailureWhenSecondRuleFails() {
        when(lengthRule.isValid("fail")).thenReturn(true);
        when(digitRule.isValid("fail")).thenReturn(false);
        when(digitRule.getErrorMessage()).thenReturn("A senha deve conter pelo menos um dígito");

        ValidationResponse response = service.validate("fail");

        assertFalse(response.valid());
        assertEquals("A senha deve conter pelo menos um dígito", response.message());
    }

    @Test
    void shouldReturnFailureWhenPasswordIsNull() {
        ValidationResponse response = service.validate(null);
        assertFalse(response.valid());
        assertEquals("A senha não pode ser nula", response.message());
    }

    @Test
    void shouldReturnFailureWhenPasswordContainsOnlyWhitespace() {
        // isBlank() retorna true apenas para strings vazias ou só com espaços/tabs/etc.
        ValidationResponse response = service.validate("   "); // apenas espaços
        assertFalse(response.valid());
        assertEquals("Password cannot contain whitespace", response.message());
    }

    @Test
    void shouldValidateSuccessfullyWithRealRules() {
        // Teste de integração entre service e regras reais (sem mocks)
        PasswordValidatorService realService = new PasswordValidatorService(List.of(
                new LengthRule(),
                new DigitRule(),
                new UppercaseRule(),
                new LowercaseRule(),
                new SpecialCharRule(),
                new NoRepeatedCharRule()
        ));

        ValidationResponse response = realService.validate("AbTp9!fok");
        assertTrue(response.valid());
        assertEquals("sua senha é valida", response.message());
    }
}