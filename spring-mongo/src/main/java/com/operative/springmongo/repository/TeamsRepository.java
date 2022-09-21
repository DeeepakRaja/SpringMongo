package com.operative.springmongo.repository;

import com.operative.springmongo.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends MongoRepository<Team,String> {
}
