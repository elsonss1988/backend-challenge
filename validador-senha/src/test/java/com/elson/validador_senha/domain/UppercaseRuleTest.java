package com.elson.validador_senha.domain;

import com.elson.validador_senha.domain.components.UppercaseRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UppercaseRuleTest {
    private final UppercaseRule rule = new UppercaseRule();

    @Test
    void shouldReturnTrueWhenPasswordContainsUppercase() {
        assertTrue(rule.isValid("Abcdef"));
        assertTrue(rule.isValid("A"));
    }

    @Test
    void shouldReturnFalseWhenNoUppercase() {
        assertFalse(rule.isValid("abcdef"));
        assertFalse(rule.isValid("123"));
    }
}
