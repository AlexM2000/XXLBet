package xxl.bet.milto.service.impl;

import xxl.bet.milto.dao.MatchesDao;
import xxl.bet.milto.dao.impl.MatchesDaoImpl;
import xxl.bet.milto.domain.Match;
import xxl.bet.milto.service.MatchesService;

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
