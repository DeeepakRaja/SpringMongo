package com.operative.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.operative.springmongo.models.Movie;

import lombok.NoArgsConstructor;

@NoArgsConstructor

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

}
