package com.operative.springmongo.controller;

import com.operative.springmongo.model.Movie;
import com.operative.springmongo.repository.MovieRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieControllerTestJUnit {

    @Autowired
    MovieController movieController;
    @Autowired
    MovieRepository movieRepository;

    Movie movie = new Movie("testID", "test Movie", "test genre", "test year", "test director", 5.0);

    @Test
    @Order(1)
    void addMovie() {
        Movie response = movieController.addMovie(movie);
        assertEquals(response.getName(),movie.getName());
//        assertTrue(movieRepository.existsById("testID"));
    }

    @Test
    @Order(2)
    public void getAllMovies() {
        List<Movie> movies = movieController.getAllMovies();
        assertEquals(movies.size(), movieRepository.count());
    }

    @Test
    public void getMoviesByPage() {
        int pageNo=1;
        int pageSize=5;
        assertEquals(pageSize,movieController.getMovies(pageNo,pageSize).size());
    }

    @Test
    void getAvgRatingByDirector() {
//        assertEquals("Christopher Nolan",movieController.getAvgRatingByDirector().get(0).get("Director"));
        assertEquals("Francis Ford Coppola",movieController.getAvgRatingByDirector().get(0).get("Director"));
    }

    @Test
    @Order(3)
    void editMovieMovieExists() {
//        Movie movie = movieRepository.findById("63241db07c594503baca8ec1").get();
        movie.setRating(9.3);
        assertEquals(movie.getName() + " Updated successfully", movieController.editMovie(movie));
    }

    @Test
    @Order(4)
    void editMovieMovieDoesNotExist() {
        Movie test = new Movie("t", "t", "t", "t", "t", 0.0);
        assertEquals(test.getName() + " doesn't exist", movieController.editMovie(test));
    }

    @Test
    @Order(5)
    void deleteMovieMovieExists() {
        assertEquals(movie.getName() + " deleted successfully", movieController.deleteMovie(movie.getId()));
    }

    @Test
    @Order(6)
    void deleteMovieMovieDoesntExist() {
        assertEquals("Movie doesn't exist", movieController.deleteMovie("5464kjl"));
    }

}