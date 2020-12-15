package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.domain.Match;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchesDao {
    List<Match> getMatchesByTournament(long tournamentId);
    List<Bet> getMatchesAfter(LocalDateTime time);
    Match createMatch(Match match);
    void deleteMatch(long id);
    List<Match> getIncompleteMatches();
}
