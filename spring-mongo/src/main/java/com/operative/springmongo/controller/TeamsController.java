package com.operative.springmongo.controller;

import com.operative.springmongo.model.Team;
import com.operative.springmongo.service.serviceInterface.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {
    @Autowired
    TeamsService teamsService;

    @GetMapping("/")
    public List<Team> getAllTeams() {
        return teamsService.getAllTeams();
    }

//    @PostMapping("/")
//    public Team addTeam(@RequestBody Team team){
//        return teamsService.addTeam(team);
//    }
}
