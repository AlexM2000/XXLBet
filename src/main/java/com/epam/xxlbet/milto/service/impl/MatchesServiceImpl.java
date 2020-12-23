package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.MatchesDao;
import com.epam.xxlbet.milto.dao.impl.MatchesDaoImpl;
import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.service.MatchesService;

import java.util.List;

/**
 * MatchesServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class MatchesServiceImpl implements MatchesService {
    private static MatchesServiceImpl instance;
    private MatchesDao matchesDao;

    private MatchesServiceImpl() {
        matchesDao = MatchesDaoImpl.getInstance();
    }

    public static MatchesServiceImpl getInstance() {
        if (instance == null) {
            instance = new MatchesServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Match> getIncompleteMatches() {
        return matchesDao.getIncompleteMatches();
    }
}
