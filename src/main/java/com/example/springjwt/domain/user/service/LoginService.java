package com.example.springjwt.domain.user.service;

import com.example.springjwt.domain.user.dto.request.LoginRequest;
import com.example.springjwt.domain.user.dto.response.LoginResponse;
import com.example.springjwt.domain.user.security.CustomUserDetails;
import com.example.springjwt.global.exception.CustomException;
import com.example.springjwt.global.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtUtil.createJwt(customUserDetails.getUsername(), customUserDetails.getAuthorities().iterator().next().getAuthority(), 60 * 60 * 1000L);

            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }
}