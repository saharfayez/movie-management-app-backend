package com.example.MovieManagementApp.service;

import com.example.MovieManagementApp.dto.OmdbMovieResponse;

import java.util.List;

public interface MovieService {
    List<OmdbMovieResponse> getAllMovies();
    OmdbMovieResponse getMovieById(String movieId);
    OmdbMovieResponse addMovie(String imdbID);
    void removeMovie(String movieId);
}
