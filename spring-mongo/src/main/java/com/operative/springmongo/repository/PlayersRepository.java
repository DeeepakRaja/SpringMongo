package com.operative.springmongo.repository;

import com.operative.springmongo.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends MongoRepository<Player,String > {
}
