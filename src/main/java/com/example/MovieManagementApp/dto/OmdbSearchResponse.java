package com.example.MovieManagementApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OmdbSearchResponse {
    List<OmdbMovieResponse> Search;
    String totalResults;
    String Response;
    String Error;
}
