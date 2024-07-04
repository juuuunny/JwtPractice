package com.example.springjwt.domain.user.controller;

import com.example.springjwt.domain.user.dto.request.LoginRequest;
import com.example.springjwt.domain.user.dto.response.LoginResponse;
import com.example.springjwt.domain.user.service.LoginService;
import com.example.springjwt.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.OK, "Login successful", loginResponse);
        return ResponseEntity.ok(response);
    }
}
