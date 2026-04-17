package com.elson.validador_senha.domain;

import com.elson.validador_senha.domain.components.LengthRule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LengthRuleTest {

    private final LengthRule rule = new LengthRule();

    @Test
    void shouldReturnTrueWhenPasswordHasNineOrMoreChars() {
        assertTrue(rule.isValid("123456789"));
        assertTrue(rule.isValid("1234567890"));
    }

    @Test
    void shouldReturnFalseWhenPasswordHasLessThanNineChars() {
        assertFalse(rule.isValid("12345678"));
        assertFalse(rule.isValid(""));
    }
}