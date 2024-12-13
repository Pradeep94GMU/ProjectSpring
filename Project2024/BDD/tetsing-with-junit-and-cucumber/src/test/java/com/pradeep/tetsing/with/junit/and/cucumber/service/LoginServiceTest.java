package com.pradeep.tetsing.with.junit.and.cucumber.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginServiceTest {

    private final LoginService loginService = new LoginService();

    @Test
    public void testValidUser() {
        boolean isValid = loginService.validateUser("user", "pass");
        assertTrue(isValid);
    }

    @Test
    public void testInvalidUser() {
        boolean isValid = loginService.validateUser("invalidUser", "wrongPass");
        assertFalse(isValid);
    }
}
