package com.operative.springmongo.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.operative.springmongo.models.Movie;
import com.operative.springmongo.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired MovieService movieService;
	
	@GetMapping("/hello-world")
	public String helloWorld(){
		return "Hello World";
	}
	
	// Get all the movies
	@GetMapping("/")
	public List<Movie> getMovies(){
		return movieService.getMovies();
	}
	
	// Get all the movies Pagination
	@GetMapping("/page")
	public List<Movie> getMoviesByPage(@RequestParam int pageNo, @RequestParam int pageSize){
		return movieService.getMoviesByPage(pageNo,pageSize);
	}
	
	// Filter movies by genre
	@GetMapping("/genre")
	public List<Movie> getByGenre(@RequestParam String genre){
		return movieService.filterByGenre(genre);
	}
	
	// FIlter by Year
	@GetMapping("/year")
	public List<Movie> getByYear(@RequestParam String year){
		return movieService.filterByYear(year);
	}
	
	@GetMapping("/gbRating")
	public List<Document> groupByRating(){
		return movieService.groupByRating();
		
	}
	
	@GetMapping("/groupBy")
	public List<Document> groupByCategory(@RequestParam String category){
		return movieService.groupByCategory(category);
	}
	
	@PostMapping("/")
	public Movie insertMovie(@RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}
	
	@PutMapping("/")
	public String updateMovie(@RequestBody Movie movie) {
		return movieService.editMovie(movie);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMovie(@PathVariable String id) {
		return movieService.deleteMovie(id);
	}
}
