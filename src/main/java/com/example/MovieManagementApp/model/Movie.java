package com.example.MovieManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    private String imdbId;

    private String title;

    @Column(name = "release_year")
    private String year;

    private String type;

    private String poster;

}
