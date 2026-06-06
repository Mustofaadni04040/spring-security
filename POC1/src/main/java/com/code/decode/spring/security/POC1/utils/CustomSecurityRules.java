package com.code.decode.spring.security.POC1.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomSecurityRules {
    public boolean userAccessDecider(String id, Authentication authentication) {
//        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          return authentication.getName().equals(id)
                  && authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_Admin"));
    }
}
