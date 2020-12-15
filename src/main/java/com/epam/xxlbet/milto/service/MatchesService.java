package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Match;

import java.util.List;

public interface MatchesService {
    List<Match> getIncompleteMatches();
}
