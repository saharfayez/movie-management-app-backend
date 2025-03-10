package com.example.MovieManagementApp.service;

import com.example.MovieManagementApp.dto.OmdbMovieResponse;

import java.util.List;

public interface OmdbService {
    List<OmdbMovieResponse> searchMovies(String s);
    OmdbMovieResponse findMovie(String imdbID);
}
