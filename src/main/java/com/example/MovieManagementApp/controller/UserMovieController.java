package com.example.MovieManagementApp.controller;

import com.example.MovieManagementApp.dto.OmdbMovieResponse;
import com.example.MovieManagementApp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserMovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<OmdbMovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/movies/{imdbd}")
    public ResponseEntity<OmdbMovieResponse> getMovieById(@PathVariable("imdbd") String imdbID) {
        return ResponseEntity.ok(movieService.getMovieById(imdbID));
    }
}
