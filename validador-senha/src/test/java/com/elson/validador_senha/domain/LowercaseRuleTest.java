package com.elson.validador_senha.domain;

import com.elson.validador_senha.domain.components.LowercaseRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LowercaseRuleTest {

    private final LowercaseRule rule = new LowercaseRule();

    @Test
    void shouldReturnTrueWhenPasswordContainsLowercase() {
        assertTrue(rule.isValid("Abcdef"));
        assertTrue(rule.isValid("a"));
    }

    @Test
    void shouldReturnFalseWhenNoLowercase() {
        assertFalse(rule.isValid("ABCDEF"));
        assertFalse(rule.isValid("123"));
    }
}
