package com.operative.springmongo.controller;

import com.operative.springmongo.model.Movie;
import com.operative.springmongo.service.serviceInterface.MovieService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public List<Movie> getAllMovies() {
        return movieService.getMovies();
    }
    @GetMapping("/page")
    public List<Movie> getMovies(
            @RequestParam( defaultValue = "0") int pageNo,
            @RequestParam( defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(Math.max(0, pageNo - 1), pageSize);
        return movieService.getMoviesByPage(pageable);
    }

    @GetMapping("/groupBy")
    public List<Document> groupByCategory(@RequestParam String category) {
        return movieService.groupByCategory(category);
    }

    @GetMapping("/avgRating")
    public List<Document> getAvgRatingByDirector() {
        return movieService.getAvgRatingByDirector();
    }

    @GetMapping("/category")
    public List<Movie> getByCategory(@RequestParam(required = false, defaultValue = "") String fieldName,
                                     @RequestParam(required = false, defaultValue = "") String fieldValue) {
        return movieService.filterByCategory(fieldName, fieldValue);
    }

    @PostMapping("/")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/")
    public String editMovie(@RequestBody Movie movie) {
        return movieService.editMovie(movie);
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable String id) {
        return movieService.deleteMovie(id);
    }
}
