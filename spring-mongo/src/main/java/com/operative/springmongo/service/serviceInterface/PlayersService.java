package com.operative.springmongo.service.serviceInterface;

import com.operative.springmongo.model.Player;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlayersService {
    List<Player> getAllPlayers();

    List<Document> lookUp();

    Player addPlayer(Player player);

    Player getPlayerById(String id);
}
