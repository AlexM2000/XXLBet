package xxl.bet.milto.service;

import xxl.bet.milto.domain.Match;

import java.util.List;

public interface MatchesService {
    List<Match> getIncompleteMatches();
}
