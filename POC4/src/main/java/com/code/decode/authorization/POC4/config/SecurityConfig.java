package com.code.decode.authorization.POC4.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        String expression = "isAuthenticated() and hasAuthority('read')";
//        dispatcher mather
          return httpSecurity.authorizeHttpRequests(auth -> auth
                  .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                  .requestMatchers("/secure/**").authenticated()
                  .anyRequest().permitAll()
          ).formLogin(Customizer.withDefaults()).build();

//        regex mathcer
//         return httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(new RegexRequestMatcher("/api/v[0-9]+", null))
//                        .hasRole("USER")
//                        .anyRequest().authenticated()
//                ).build();

//        request matcher
//        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/status/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/testMe/**").hasRole("Admin")
//                        .requestMatchers(HttpMethod.POST, "/testMe/**").hasRole("Manager")
//                        .anyRequest().authenticated()
//                ).build();

//        return httpSecurity.httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(
//                        request -> request
////                                .anyRequest().permitAll()
////                                .anyRequest().hasAuthority("write")
////                                .anyRequest().hasAnyAuthority("read, write")
////                                .anyRequest().hasRole("Admin")
//                                .anyRequest().access(new WebExpressionAuthorizationManager(expression))
//                )
//                .build();
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
