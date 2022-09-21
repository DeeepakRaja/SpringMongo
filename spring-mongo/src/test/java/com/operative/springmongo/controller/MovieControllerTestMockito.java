package com.operative.springmongo.controller;

import com.operative.springmongo.model.Movie;
import com.operative.springmongo.service.serviceInterface.MovieService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = MovieController.class)
public class MovieControllerTestMockito {

    @Autowired MockMvc mockMvc;

    @InjectMocks
    MovieController movieController;
    @Mock
    MovieService movieService;

    Movie movie=new Movie("0001","test","","","",0);
    List<Movie> movies=new ArrayList<>();

    @Test
    public void getAllMovies() throws Exception {
        movies.add(movie);
        when(movieService.getMovies()).thenReturn(movies);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/00001").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();

        String example="[\n" +
                "    {\n" +
                "        \"id\": \"63241db07c594503baca8ec1\",\n" +
                "        \"name\": \"The Shawshank Redemption\",\n" +
                "        \"genre\": \"Drama\",\n" +
                "        \"releaseYear\": \"1994\",\n" +
                "        \"director\": \"Frank Darabont\",\n" +
                "        \"rating\": 9.3\n" +
                "    }\n" +
                "]";
        JSONAssert.assertEquals(example,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void addMovie() {
    }

    @Test
    public void editMovie() {
    }

    @Test
    public void deleteMovie() {
    }
}