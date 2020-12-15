package com.emap.xxlbet.milto.dao;

import com.emap.xxlbet.milto.domain.Bet;
import com.emap.xxlbet.milto.domain.Match;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchesDao {
    List<Match> getMatchesByTournament(long tournamentId);
    List<Bet> getMatchesAfter(LocalDateTime time);
    Match createMatch(Match match);
    void deleteMatch(long id);
    List<Match> getIncompleteMatches();
}
