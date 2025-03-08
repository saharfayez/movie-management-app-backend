package com.example.MovieManagementApp.service;

import com.example.MovieManagementApp.dto.LoginRequest;
import com.example.MovieManagementApp.dto.LoginResponse;
import com.example.MovieManagementApp.security.CustomUserDetails;

public interface UserService {
    CustomUserDetails findUser(LoginRequest loginRequest);
    LoginResponse login(LoginRequest loginRequest);
}
