package io.java.ipldashboard.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.java.ipldashboard.model.Team;
import io.java.ipldashboard.repository.MatchRepository;
import io.java.ipldashboard.repository.TeamRepository;

@CrossOrigin
@RestController
public class TeamController {
    
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepository.findByTeamName(teamName);    
        team.setMatches(this.matchRepository.findlatestMatchesByTeam(teamName,4));
        return team;
    }


}
