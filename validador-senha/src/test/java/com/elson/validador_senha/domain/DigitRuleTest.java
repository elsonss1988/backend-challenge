package com.elson.validador_senha.domain;

import com.elson.validador_senha.domain.components.DigitRule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DigitRuleTest {

    private final DigitRule rule = new DigitRule();

    @Test
    void shouldReturnTrueWhenPasswordContainsAtLeastOneDigit() {
        assertTrue(rule.isValid("abc1def"));
        assertTrue(rule.isValid("123"));
    }

    @Test
    void shouldReturnFalseWhenPasswordContainsNoDigit() {
        assertFalse(rule.isValid("abcdef"));
        assertFalse(rule.isValid("!@#$%"));
    }
}
