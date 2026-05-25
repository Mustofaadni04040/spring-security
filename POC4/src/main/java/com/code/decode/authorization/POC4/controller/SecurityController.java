package com.code.decode.authorization.POC4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/testMe")
    public String getTest() {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(a -> System.out.println(a));
        return "code test";
    }

    @GetMapping("/status")
    public String getStatus() {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(a -> System.out.println(a));
        return "code status";
    }

    @PreAuthorize("#id == authentication.principal.username")
    @GetMapping("testMe/{id}")
    public String testGetV1DemoEndpoint(@PathVariable String id) {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(a -> System.out.println(a));

        return "Access granted to user: " + id;
    }
}
