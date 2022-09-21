package com.operative.springmongo.service.serviceInterface;

import com.operative.springmongo.model.Movie;
import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    List<Movie> getMovies();

    String editMovie(Movie movie);

    String deleteMovie(String id);

    Movie addMovie(Movie movie);

    List<Movie> filterByCategory(String fieldName,String fieldValue);

    List<Movie> getMoviesByPage(Pageable pageable);

    List<Document> groupByCategory(String category);

    List<Document> getAvgRatingByDirector();
}
