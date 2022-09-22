package com.operative.springmongo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.operative.springmongo.models.Movie;
import com.operative.springmongo.repository.MovieRepository;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
	@InjectMocks
	private MovieServiceImpl movieService;
	@Mock
	private MovieRepository movieRepository;

	@Test
	public void findAllMovies() throws Exception {
		when(movieRepository.findAll()).thenReturn(Arrays.asList(
				new Movie("63241d477c594503baca8ebe", "Forrest Gump", "Drama", "1994", "Robert Zemeckis", 8.8),
				new Movie("63241dbf7c594503baca8ec2", "Gladiator", "Drama", "2009", "Ridley Scott", 8.5)));
		List<Movie> movies = movieService.getMovies();
		assertEquals(2, movies.size());
	}

	@Test
	public void sumOfRatings() {
		when(movieRepository.findAll()).thenReturn(Arrays.asList(
				new Movie("63241d477c594503baca8ebe", "Forrest Gump", "Drama", "1994", "Robert Zemeckis", 8.8),
				new Movie("63241dbf7c594503baca8ec2", "Gladiator", "Drama", "2009", "Ridley Scott", 8.5)));

		assertEquals(17.3, movieService.sumOfRatings());
	}

	@Test
	public void editMovieInDb() {
		Movie movie = new Movie("63241d477c594503baca8ebe", "Forrest Gump", "Drama", "1994", "Robert Zemeckis", 8.8);
		when(movieRepository.existsById("63241d477c594503baca8ebe")).thenReturn(true);
		when(movieRepository.save(movie)).thenReturn(movie);

		assertEquals("Forrest Gump updated successfully", movieService.editMovie(movie));
	}

	@Test
	public void editMovieNotInDb() {
		Movie movie = new Movie("63241d477c594503baca8ebe", "Forrest Gump", "Drama", "1994", "Robert Zemeckis", 8.8);
		when(movieRepository.existsById("63241d477c594503baca8ebe")).thenReturn(false);

		assertEquals("Forrest Gump not found", movieService.editMovie(movie));
	}

}
