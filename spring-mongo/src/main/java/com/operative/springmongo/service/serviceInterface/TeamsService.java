package com.operative.springmongo.service.serviceInterface;

import com.operative.springmongo.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamsService {
    List<Team> getAllTeams();

    Team addTeam(Team team);
}
