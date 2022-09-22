package com.operative.springmongo.service;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.operative.springmongo.models.Movie;

@Service
public interface MovieService {

//	String helloW();

	List<Movie> getMovies();

	List<Movie> getMoviesByPage(int pageNo, int pageSize);

	List<Movie> filterByGenre(String genre);

	List<Movie> filterByYear(String year);

	List<Document> groupByCategory(String category);

	Movie addMovie(Movie movie);

	String editMovie(Movie movie);

	String deleteMovie(String id);

	List<Document> groupByRating();

}
