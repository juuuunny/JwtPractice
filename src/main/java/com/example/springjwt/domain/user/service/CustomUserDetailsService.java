package com.example.springjwt.domain.user.service;

import com.example.springjwt.domain.user.security.CustomUserDetails;
import com.example.springjwt.domain.user.entity.User;
import com.example.springjwt.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user= userRepository.findByUsername(username);
        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
