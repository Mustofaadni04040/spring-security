package com.code.decode.authorization.POC4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        request -> request
//                                .anyRequest().permitAll()
//                                .anyRequest().hasAuthority("write")
//                                .anyRequest().hasAnyAuthority("read, write")
                                .anyRequest().hasRole("Admin"))
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        UserDetails userDetailsObj1 = User.withUsername("code")
                .password("decode")
//                .authorities("read")
//                .roles("Admin")
                .authorities("ROLE_Manager")
                .build();

        UserDetails userDetailsObj2 = User.withUsername("code1")
                .password("decode1")
//                .authorities("write")
//                .roles("Manager")
                .authorities("ROLE_Admin")
                .build();
        inMemoryUserDetailsManager.createUser(userDetailsObj1);
        inMemoryUserDetailsManager.createUser(userDetailsObj2);

        return inMemoryUserDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
