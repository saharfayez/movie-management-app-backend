package com.example.MovieManagementApp.controller;

import com.example.MovieManagementApp.dto.OmdbMovieResponse;
import com.example.MovieManagementApp.service.MovieService;
import com.example.MovieManagementApp.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/movies")
public class AdminMovieController {

    @Autowired
    OmdbService omdbService;

    @Autowired
    MovieService movieService;

    @GetMapping("/search")
    public ResponseEntity<List<OmdbMovieResponse>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(omdbService.searchMovies(title));
    }

    @PostMapping("/{imdbID}")
    public ResponseEntity<OmdbMovieResponse> addMovie(@PathVariable String imdbID) {
        return new ResponseEntity<>(movieService.addMovie(imdbID), HttpStatus.CREATED);
    }

    @DeleteMapping("/{imdbID}")
    public ResponseEntity<OmdbMovieResponse> deleteMovie(@PathVariable String imdbID) {
        movieService.removeMovie(imdbID);
        return ResponseEntity.noContent().build();
    }

}
