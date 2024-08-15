package com.example.jsoup.controller;

import com.example.jsoup.dto.UserDTO;
import com.example.jsoup.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public UserDTO login() {
        UserDTO user = loginService.crawling();
        return user;
    }
}
