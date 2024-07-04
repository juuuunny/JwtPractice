package com.example.springjwt.domain.user.service;

import com.example.springjwt.domain.user.dto.request.JoinRequest;
import com.example.springjwt.domain.user.entity.User;
import com.example.springjwt.domain.user.repository.UserRepository;
import com.example.springjwt.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinRequest joinRequest){
        String username = joinRequest.getUsername();
        String password = joinRequest.getPassword();

        if(userRepository.existsByUsername(username)){
            throw new CustomException(HttpStatus.CONFLICT, "이미 존재하는 Username입니다.");
        }

        userRepository.save(User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_ADMIN")
                .build());
    }

}
