package com.example.MovieManagementApp.service.impl;

import com.example.MovieManagementApp.dto.OmdbMovieResponse;
import com.example.MovieManagementApp.dto.OmdbSearchResponse;
import com.example.MovieManagementApp.exception.MovieNotFoundException;
import com.example.MovieManagementApp.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OmdbServiceImpl implements OmdbService {

    @Autowired
    WebClient webClient;

    @Override
    public List<OmdbMovieResponse> searchMovies(String title) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("s", title).build())
                .retrieve()
                .bodyToMono(OmdbSearchResponse.class)
                .flatMap(response -> {
                    if (response == null || response.getSearch() == null || response.getSearch().isEmpty()) {
                        return Mono.error(new MovieNotFoundException("No movies found for title: " + title));
                    }
                    return Mono.just(response.getSearch());
                })
                .block();
    }

    @Override
    public OmdbMovieResponse findMovie(String imdbID) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("i", imdbID).build())
                .retrieve()
                .bodyToMono(OmdbMovieResponse.class)
                .flatMap(movie -> {
                    if (movie == null || movie.getImdbId() == null) {
                        return Mono.error(new MovieNotFoundException("Movie not found for imdbID: " + imdbID));
                    }
                    return Mono.just(movie);
                })
                .block();
    }

}
