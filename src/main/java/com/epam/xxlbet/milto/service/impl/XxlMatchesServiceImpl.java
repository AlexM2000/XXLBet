package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.MatchesDao;
import com.epam.xxlbet.milto.dao.impl.MatchesDaoImpl;
import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.service.MatchesService;

import java.util.List;

public class XxlMatchesServiceImpl implements MatchesService {
    private static XxlMatchesServiceImpl instance;
    private MatchesDao matchesDao;

    private XxlMatchesServiceImpl() {
        matchesDao = MatchesDaoImpl.getInstance();
    }

    public static XxlMatchesServiceImpl getInstance() {
        if (instance == null) {
            instance = new XxlMatchesServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Match> getIncompleteMatches() {
        return matchesDao.getIncompleteMatches();
    }
}
