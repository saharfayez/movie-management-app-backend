package com.example.MovieManagementApp.service.impl;

import com.example.MovieManagementApp.dto.LoginRequest;
import com.example.MovieManagementApp.dto.LoginResponse;
import com.example.MovieManagementApp.repository.UserRepository;
import com.example.MovieManagementApp.security.CustomUserDetails;
import com.example.MovieManagementApp.security.JwtService;
import com.example.MovieManagementApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Override
    public CustomUserDetails findUser(LoginRequest loginRequest) {

        return userRepository.findByUsername(loginRequest.getUsername())
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        CustomUserDetails customUserDetails = findUser(loginRequest);

        String token = jwtService.generateToken(customUserDetails);
        return LoginResponse.builder()
                .token(token)
                .role(customUserDetails.getAuthorities().toString()).build();

    }
}
