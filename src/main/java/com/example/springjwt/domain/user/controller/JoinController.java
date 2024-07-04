package com.example.springjwt.domain.user.controller;

import com.example.springjwt.domain.user.dto.request.JoinRequest;
import com.example.springjwt.domain.user.service.JoinService;
import com.example.springjwt.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> join(@RequestBody JoinRequest joinRequest) {
        joinService.join(joinRequest);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED, "User registered successfully", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

