package com.example.MovieManagementApp.service.impl;

import com.example.MovieManagementApp.dto.OmdbMovieResponse;
import com.example.MovieManagementApp.exception.MovieAlreadyExistsException;
import com.example.MovieManagementApp.exception.MovieNotFoundException;
import com.example.MovieManagementApp.model.Movie;
import com.example.MovieManagementApp.repository.MovieRepository;
import com.example.MovieManagementApp.service.MovieService;
import com.example.MovieManagementApp.service.OmdbService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    OmdbService omdbService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<OmdbMovieResponse> getAllMovies() {
        return List.of(modelMapper.map(movieRepository.findAll(), OmdbMovieResponse[].class));
    }

    @Override
    public OmdbMovieResponse getMovieById(String movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not Found with ID" + movieId));
        return modelMapper.map(movie, OmdbMovieResponse.class);
    }

    @Override
    public OmdbMovieResponse addMovie(String imdbID) {
        if (movieRepository.existsById(imdbID)) {
            throw new MovieAlreadyExistsException("Movie already exists with imdbID: " + imdbID);
        }

        Movie movie = modelMapper.map(omdbService.findMovie(imdbID), Movie.class);
        movieRepository.save(movie);
        return modelMapper.map(movie, OmdbMovieResponse.class);
    }

    @Override
    public void removeMovie(String imdbID) {
        if (!movieRepository.existsById(imdbID)) {
            throw new MovieNotFoundException("Movie not Found with imdbID:  " + imdbID);
        }
        movieRepository.deleteById(imdbID);
    }

}
