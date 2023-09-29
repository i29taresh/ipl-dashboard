package io.java.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.java.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.season = :season order by date desc")
    List<Match> getMatchesByTeamSeason(@Param("teamName") String teamName, @Param("season") long season);
    // List<Match> getByTeam1AndSeasonOrTeam2AndSeasonOrderByDateDesc(String teamName1, long year1, String teamName2, long year2);
    List<Match> getBySeasonOrderByDateDesc(long year);
    
    default List<Match> findlatestMatchesByTeam(String teamName, int count){
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0,count));
    }
}
