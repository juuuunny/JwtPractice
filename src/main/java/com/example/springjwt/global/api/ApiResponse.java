package com.example.springjwt.global.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private final HttpStatus status;
    private final String message;
    private final T data;
}
