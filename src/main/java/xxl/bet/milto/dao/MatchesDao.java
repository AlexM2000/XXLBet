package xxl.bet.milto.dao;

import xxl.bet.milto.domain.Bet;
import xxl.bet.milto.domain.Match;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchesDao {
    List<Match> getMatchesByTournament(long tournamentId);
    List<Bet> getMatchesAfter(LocalDateTime time);
    Match createMatch(Match match);
    void deleteMatch(long id);
}
