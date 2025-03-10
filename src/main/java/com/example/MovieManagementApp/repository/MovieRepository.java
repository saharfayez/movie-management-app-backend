package com.example.MovieManagementApp.repository;

import com.example.MovieManagementApp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
