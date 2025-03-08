package com.example.MovieManagementApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private String role;
}

