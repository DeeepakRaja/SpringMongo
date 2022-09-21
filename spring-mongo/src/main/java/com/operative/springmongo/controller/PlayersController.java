package com.operative.springmongo.controller;

import com.operative.springmongo.model.Player;
import com.operative.springmongo.service.serviceInterface.PlayersService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayersController {
    @Autowired
    PlayersService playersService;

    @GetMapping("/")
    public List<Player> getAllPlayers() {
        return playersService.getAllPlayers();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerDetails(@PathVariable String id){
        return playersService.getPlayerById(id);
    }

    @PostMapping("/")
    public Player addPlayer(@RequestBody Player player){
        return playersService.addPlayer(player);
    }

    @GetMapping("/lookup")
    public List<Document> lookUp() {
        return playersService.lookUp();
    }
}
