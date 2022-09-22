package com.operative.springmongo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.operative.springmongo.models.Movie;
import com.operative.springmongo.service.MovieService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieController.class)
public class MovieControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	MovieController movieController;

	@MockBean
	MovieService movieService;

	@Test
	public void helloWorldUsingAssert() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("http://localhost:8080/movies/hello-world")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(req).andReturn();
		
		assertEquals("Hello World", result.getResponse().getContentAsString());
	}

	@Test
	public void helloWorldUsingMatchers() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/movies/hello-world")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("Hello World")).andReturn();
	}

	@Test
	public void showAllMovies() throws Exception {
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("63241d477c594503baca8ebe", "Forrest Gump", "Drama", "1994", "Robert Zemeckis", 8.8));
		movies.add(new Movie("63241dbf7c594503baca8ec2", "Gladiator", "Drama", "2009", "Ridley Scott", 8.5));

		String expectedResult = "[\r\n" + "    {\r\n" + "        \"id\": \"63241d477c594503baca8ebe\",\r\n"
				+ "        \"name\": \"Forrest Gump\",\r\n" + "        \"genre\": \"Drama\",\r\n"
				+ "        \"releaseYear\": \"1994\",\r\n" + "        \"director\": \"Robert Zemeckis\",\r\n"
				+ "        \"rating\": 8.8\r\n" + "    }," + "{\r\n"
				+ "        \"id\": \"63241dbf7c594503baca8ec2\",\r\n" + "        \"name\": \"Gladiator\",\r\n"
				+ "        \"genre\": \"Drama\",\r\n" + "        \"releaseYear\": \"2009\",\r\n"
				+ "        \"director\": \"Ridley Scott\",\r\n" + "        \"rating\": 8.5\r\n" + "    }\r\n" + "]";

		when(movieService.getMovies()).thenReturn(movies);

		RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/movies/")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(expectedResult)).andReturn();

	}

	@Test
	public void addMovie() throws Exception {
		String movieDetails = "{\r\n" + "        \"id\": \"63241d477c594503baca8ebe\",\r\n"
				+ "        \"name\": \"Forrest Gump\",\r\n" + "        \"genre\": \"Drama\",\r\n"
				+ "        \"releaseYear\": \"1994\",\r\n" + "        \"director\": \"Robert Zemeckis\",\r\n"
				+ "        \"rating\": 8.8\r\n" + "    }";

		Movie movie = new Movie("63241d477c594503baca8ebe", "Forrest Gump", "Drama", "1994", "Robert Zemeckis", 8.8);
		when(movieService.addMovie(movie)).thenReturn(movie);

		RequestBuilder requerst = MockMvcRequestBuilders.post("http://localhost:8080/movies/")
				.accept(MediaType.APPLICATION_JSON).content(movieDetails).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requerst).andExpect(status().isOk()).andReturn();
	}

}
