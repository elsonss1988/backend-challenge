package com.elson.validador_senha.domain;

import com.elson.validador_senha.domain.components.SpecialCharRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpecialCharRuleTest {

    private final SpecialCharRule rule = new SpecialCharRule();

    @Test
    void shouldReturnTrueWhenPasswordContainsSpecialChar() {
        assertTrue(rule.isValid("abc!def"));
        assertTrue(rule.isValid("@"));
    }

    @Test
    void shouldReturnFalseWhenNoSpecialChar() {
        assertFalse(rule.isValid("abcdef1"));
        assertFalse(rule.isValid("ABC123"));
    }
}
