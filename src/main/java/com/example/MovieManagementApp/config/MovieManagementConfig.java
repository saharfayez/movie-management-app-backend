package com.example.MovieManagementApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MovieManagementConfig {

    private String apiKey = "aaa66654";

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    WebClient webClient() {

        return WebClient.builder()
                .baseUrl("https://www.omdbapi.com/")
                .filter(apiKeyFilter())
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    private ExchangeFilterFunction apiKeyFilter() {
        return (request, next) -> {
            URI updatedUri = UriComponentsBuilder.fromUri(request.url())
                    .queryParam("apikey", apiKey)
                    .build(true)
                    .toUri();
            ClientRequest modifiedRequest = ClientRequest.from(request)
                    .url(updatedUri)
                    .build();
            return next.exchange(modifiedRequest);
        };
    }

}
