package com.code.decode.spring.security.POC1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/testMe")
    public String baseHandler() {
        return "code decode";
    }
}
