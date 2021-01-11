package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.domain.Match;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * MatchesDao.
 *
 * @author Aliaksei Milto
 */
public interface MatchesDao {
    List<Match> getMatchesByTournament(long tournamentId);
    List<Bet> getMatchesAfter(LocalDateTime time);
    long createMatch(Long tournamentId, BigDecimal drawCoefficient, LocalDateTime dateStarted);
    void deleteMatch(long id);
    List<Match> getIncompleteMatches();
}
