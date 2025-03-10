package com.example.MovieManagementApp.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String msg) {
        super(msg);
    }
}
