package xxl.bet.milto.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.dao.BetsDao;
import xxl.bet.milto.domain.Bet;
import xxl.bet.milto.utils.PropertyLoader;
import xxl.bet.milto.utils.connection.ConnectionPool;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static xxl.bet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_BETS;

public class BetsDaoImpl implements BetsDao {
    private static final Logger LOG = LoggerFactory.getLogger(BetsDaoImpl.class);
    private static BetsDaoImpl instance;
    private ConnectionPool connectionPool;

    private BetsDaoImpl() {
        try {
            PropertyLoader.getInstance().init(FILE_WITH_QUERIES_FOR_TABLE_BETS);
        } catch (IOException e) {
            LOG.error("Could not load queries for database! Exiting...");
            System.exit(1);
        }

        connectionPool = ConnectionPool.getInstance();
    }

    public static BetsDaoImpl getInstance() {
        if (instance == null) {
            instance = new BetsDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getBetsHistoryByUser(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getIncompleteBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getWinningBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getDefeatBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public Bet createBet(long matchId, BigDecimal sum, long expectedWinnerId) {
        return null;
    }
}
