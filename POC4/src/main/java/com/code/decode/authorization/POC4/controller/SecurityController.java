package com.code.decode.authorization.POC4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/testMe")
    public String baseHandler() {
        return "code decode";
    }
}
