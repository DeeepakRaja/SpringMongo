package com.operative.springmongo.model;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter

@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String name;
    private String genre;
    private String releaseYear;
    private String director;
    private double rating;

    public Movie(String id, String name, String genre, String releaseYear, String director, double rating) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.rating = rating;
    }
}
