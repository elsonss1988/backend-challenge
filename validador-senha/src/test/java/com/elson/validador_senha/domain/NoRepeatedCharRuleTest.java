package com.elson.validador_senha.domain;

import com.elson.validador_senha.domain.components.NoRepeatedCharRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoRepeatedCharRuleTest {

    private final NoRepeatedCharRule rule = new NoRepeatedCharRule();


    @Test
    void shouldReturnTrueWhenAllCharsAreUnique() {
        assertTrue(rule.isValid("AbTp9!fok"));
        assertTrue(rule.isValid("abc"));
    }

    @Test
    void shouldReturnFalseWhenThereAreRepeatedChars() {
        assertFalse(rule.isValid("AA"));
        assertFalse(rule.isValid("aba"));
        assertFalse(rule.isValid("AbTp9!foA")); // 'A' repetido
    }

    @Test
    void caseSensitiveConsideration() {
        // 'A' e 'a' deve passar
        assertTrue(rule.isValid("Aa"));
    }
}
