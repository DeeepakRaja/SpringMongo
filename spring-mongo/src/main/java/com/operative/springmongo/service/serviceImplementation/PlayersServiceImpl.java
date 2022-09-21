package com.operative.springmongo.service.serviceImplementation;

import com.operative.springmongo.model.Player;
import com.operative.springmongo.model.Team;
import com.operative.springmongo.repository.PlayersRepository;
import com.operative.springmongo.repository.TeamsRepository;
import com.operative.springmongo.service.serviceInterface.PlayersService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersServiceImpl implements PlayersService {
    @Autowired
    PlayersRepository playersRepository;
    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Player> getAllPlayers() {
        return playersRepository.findAll();
    }

    @Override
    public Player addPlayer(Player player) {
        if (player.getTeam() != null) {
            teamsRepository.save(player.getTeam());
        }
        return playersRepository.save(player);
    }

    @Override
    public Player getPlayerById(String id) {
        if (playersRepository.existsById(id)) {
            return playersRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Document> lookUp() {
//        AddFieldsOperation add1 = Aggregation.addFields().addFieldWithValue("team_id", ArrayOperators
//                .ArrayElemAt(ObjectOperators.ObjectToArray.valueOfToArray()));
        LookupOperation lookUp = Aggregation.lookup("teams", "team.id", "_id", "team");
        UnwindOperation unwindTeam = Aggregation.unwind("team");
        ProjectionOperation project = Aggregation.project().
                andExpression("name").as("Name")
                .andExpression("age").as("Age")
                .andExpression("nationality").as("Nationality")
                .andExpression("team.name").as("Team Name")
                .andExclude("_id");

        Aggregation aggregation = Aggregation.newAggregation(lookUp, unwindTeam, project);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Player.class, Document.class);
        return results.getMappedResults();
    }

}
