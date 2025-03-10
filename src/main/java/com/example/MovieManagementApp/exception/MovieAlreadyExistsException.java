package com.example.MovieManagementApp.exception;

public class MovieAlreadyExistsException extends RuntimeException {

    public MovieAlreadyExistsException(String msg) {
        super(msg);
    }
}
