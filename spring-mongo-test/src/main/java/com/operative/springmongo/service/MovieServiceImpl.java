package com.operative.springmongo.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.operative.springmongo.models.Movie;
import com.operative.springmongo.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Movie> getMovies() {
		return movieRepository.findAll();
	}

	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public String editMovie(Movie movie) {
		if (movieRepository.existsById(movie.getId())) {
			movieRepository.save(movie);
			return movie.getName() + " updated successfully";
		}
		return movie.getName() + " not found";
	}

	public String deleteMovie(String id) {
		if (movieRepository.existsById(id)) {
			Movie temp = movieRepository.findById(id).get();
			movieRepository.deleteById(id);
			return temp.getName() + " deleted successfully";
		}
		return "Movie not found";
	}

	public List<Movie> filterByGenre(String genre) {
		Query query = new Query().addCriteria(Criteria.where("genre").is(genre));
		query.with(Sort.by(Direction.DESC, "rating"));
		return mongoTemplate.find(query, Movie.class);
	}

	public List<Movie> filterByYear(String year) {
		Query query = new Query().addCriteria(Criteria.where("releaseYear").is(year));
		query.with(Sort.by(Direction.DESC, "rating"));
		return mongoTemplate.find(query, Movie.class);
	}

	public List<Movie> getMoviesByPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Query query = new Query().with(pageable).with(Sort.by(Direction.DESC, "rating"));
		return mongoTemplate.find(query, Movie.class);
	}

	public List<Document> groupByRating() {
		GroupOperation group = Aggregation.group("rating").count().as("Movie Count");
		SortOperation sort = Aggregation.sort(Direction.DESC, "Movie Count");
		Aggregation aggregation = Aggregation.newAggregation(group,sort);
		AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, Movie.class, Document.class);
		return result.getMappedResults();
	}

	@Override
	public List<Document> groupByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double sumOfRatings() {
		List<Movie> movies=movieRepository.findAll();
		double sum=0;
		for(Movie movie:movies) {
			sum+=movie.getRating();
		}
		return sum;
	}

}
