package io.java.ipldashboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.java.ipldashboard.model.Match;
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

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam long year){
        // return this.matchRepository.getByTeam1AndSeasonOrTeam2AndSeasonOrderByDateDesc(teamName,year,teamName,year);
        return this.matchRepository.getMatchesByTeamSeason(teamName,year);
    }

    @GetMapping("/matches")
    public List<Match> getMatchesForSeason(@RequestParam long year){
        return this.matchRepository.getBySeasonOrderByDateDesc(year);
    }


}
