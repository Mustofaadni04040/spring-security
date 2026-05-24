package com.code.decode.spring.security.POC1.service;

import com.code.decode.spring.security.POC1.entity.User;
import com.code.decode.spring.security.POC1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        if(user.isPresent()) {
            return (UserDetails) user.get();
        }

        throw new UsernameNotFoundException("This username is not found" + username);
    }
}
