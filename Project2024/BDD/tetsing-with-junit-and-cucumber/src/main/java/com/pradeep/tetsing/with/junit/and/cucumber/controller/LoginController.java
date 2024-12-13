package com.pradeep.tetsing.with.junit.and.cucumber.controller;


import com.pradeep.tetsing.with.junit.and.cucumber.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @PostMapping
    public String login(@RequestBody User user) {
        // Call login service (pseudo implementation here)
        return "Success";
    }
}
