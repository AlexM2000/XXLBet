package com.emap.xxlbet.milto.service;

import com.emap.xxlbet.milto.domain.Match;

import java.util.List;

public interface MatchesService {
    List<Match> getIncompleteMatches();
}
