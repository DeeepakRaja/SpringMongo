package com.operative.springmongo.service.serviceImplementation;

import com.operative.springmongo.model.Movie;
import com.operative.springmongo.repository.MovieRepository;
import com.operative.springmongo.service.serviceInterface.MovieService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll(
                Sort.by(Sort.Direction.DESC, "rating")
        );
    }

    @Override
    public String editMovie(Movie movie) {
        if (movieRepository.existsById(movie.getId())) {
            movieRepository.save(movie);
            return movie.getName() + " Updated successfully";
        }
        return movie.getName() + " doesn't exist";
    }

    @Override
    public String deleteMovie(String id) {
        if (movieRepository.existsById(id)) {
            String name = movieRepository.findById(id).get().getName() ;
            movieRepository.deleteById(id);
            return name + " deleted successfully";
        }
        return "Movie doesn't exist";
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> filterByCategory(String fieldName, String fieldValue) {
        Query query = new Query();

        if (!fieldName.equals("") && !fieldValue.equals("")){
            query.addCriteria(Criteria.where(fieldName).is(fieldValue));
            query.with(Sort.by(Sort.Direction.ASC, "name"));
            return mongoTemplate.find(query, Movie.class, "movies");
        }
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
    }

    @Override
    public List<Movie> getMoviesByPage(Pageable pageable) {
        Query query= new Query();
        query.with(pageable).with(Sort.by(Sort.Direction.DESC,"rating"));
        return mongoTemplate.find(query,Movie.class,"movies");
    }

    @Override
    public List<Document> groupByCategory(String category) {
        GroupOperation group= Aggregation.group(category).count().as("count");
        SortOperation sort=Aggregation.sort(Sort.Direction.DESC,"count");
        ProjectionOperation project = Aggregation.project()
                .andExpression("_id").as(category)
                .andExpression("count").as("Movie Count")
                .andExclude("_id");
        Aggregation aggregation=Aggregation.newAggregation(group,sort,project);

        AggregationResults<Document> aggregationResults =  mongoTemplate.aggregate(aggregation,Movie.class,Document.class);
        return aggregationResults.getMappedResults();
    }

    @Override
    public List<Document> getAvgRatingByDirector() {
        GroupOperation groupByDir = Aggregation.group("director")
                .avg("rating").as("Average Rating");
        SortOperation sort = Aggregation.sort(Sort.Direction.DESC,"Average Rating");
        ProjectionOperation project = Aggregation.project().
                andExpression("_id").as("Director")
                .andInclude("Average Rating")
                .andExclude("_id");

        Aggregation aggregation=Aggregation.newAggregation(groupByDir,sort,project);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation,Movie.class,Document.class);

        return results.getMappedResults();
    }
}
