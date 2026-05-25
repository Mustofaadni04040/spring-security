package com.code.decode.authorization.POC4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/testMe")
    public String getTest() {
        return "code test";
    }

    @GetMapping("/status")
    public String getStatus() {
        return "code status";
    }
}
