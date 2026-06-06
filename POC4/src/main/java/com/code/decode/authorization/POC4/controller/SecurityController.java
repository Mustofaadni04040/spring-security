package com.code.decode.authorization.POC4.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PreAuthorize("""
        hasRole("Admin")
        and #id == authentication.principal.username
    """)
    @GetMapping("testMultiLine/{id}")
    public String testGetV1DemoEndpoint2(@PathVariable String id) {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(a -> System.out.println(a));

        return "Access granted to user: " + id;
    }

    @PreAuthorize("@customSecurityRules.userAccessDecider(#id, authentication)")
    @GetMapping("testMultiLineWithCustomBean/{id}")
    public String testGetV1DemoEndpoint3(@PathVariable String id) {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(a -> System.out.println(a));

        return "Access granted to user: " + id;
    }

    @PostAuthorize("""
        returnObject == "Access granted"
        and #id == authentication.name
    """)
    @GetMapping("testMultiLineWithCustomBean/{id}")
    public String testGetV1DemoEndpoint4(@PathVariable String id) {

        return "Access granted";
    }

    @GetMapping("testPreFilter")
    @PreFilter("""
        hasRole("Admin")
        and filterObject.contains('hack')
    """)
    public List<String> testPreFilter(@RequestBody List<String> listToBDeletedAndFiltered) {
        return listToBDeletedAndFiltered;
    }

    @GetMapping("testPostFilter")
    @PreFilter("""
        hasRole("Admin")
        and !filterObject.contains('password')
    """)
    public List<String> testPostFilter() {
        return List.of("password", "code", "decode", "password_new");
    }
}
