package com.operative.springmongo.service.serviceImplementation;

import com.operative.springmongo.model.Team;
import com.operative.springmongo.repository.TeamsRepository;
import com.operative.springmongo.service.serviceInterface.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {
    @Autowired
    TeamsRepository teamsRepository;
    @Override
    public List<Team> getAllTeams() {
        return teamsRepository.findAll();
    }

    @Override
    public Team addTeam(Team team) {
        return teamsRepository.save(team);
    }
}
