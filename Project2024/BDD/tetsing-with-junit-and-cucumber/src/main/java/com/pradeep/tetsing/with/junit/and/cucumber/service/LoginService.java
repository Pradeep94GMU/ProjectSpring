package com.pradeep.tetsing.with.junit.and.cucumber.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean validateUser(String username, String password) {
        // Pseudo logic for validation
        return "user".equals(username) && "pass".equals(password);
    }
}
