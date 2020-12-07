package xxl.bet.milto.dao.impl;

import xxl.bet.milto.dao.BetsDao;
import xxl.bet.milto.domain.Bet;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static xxl.bet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_BETS;
import static xxl.bet.milto.utils.XxlBetConstants.SELECT_INCOMPLETE_BETS_BY_USER;

/**
 * BetsDaoImpl.
 *
 * @author alexm2000
 */
public class BetsDaoImpl extends AbstractDao implements BetsDao {
    private static final String ID = "id";
    private static final String MATCH_ID = "match_id";
    private static final String RESULT_ID = "result_id";
    private static final String DATE_CREATED = "date_created";
    private static final String SUM = "sum";
    private static final String EXPECTED_WINNER_ID = "expected_winner_id";
    private static final String USER_ID = "user_id";

    private static BetsDaoImpl instance;


    private BetsDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_BETS);
    }

    public static BetsDaoImpl getInstance() {
        if (instance == null) {
            instance = new BetsDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Bet> getAllIncompleteBetsByUser(final String email, final String phoneNumber) {
        List<Bet> bets = new ArrayList<>();

        try {
            Connection connection = getConnectionPool().getConnection();

            PreparedStatement statement = connection.prepareStatement(getSql(FILE_WITH_QUERIES_FOR_TABLE_BETS, SELECT_INCOMPLETE_BETS_BY_USER));

            statement.setString(1, email);
            statement.setString(2, phoneNumber);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bet bet = new Bet();
                bet.setId(resultSet.getLong(ID));
                bet.setMatchId(resultSet.getLong(MATCH_ID));
                bet.setDateCreated(resultSet.getTimestamp(DATE_CREATED));
                bet.setSum(resultSet.getBigDecimal(SUM));
                bet.setExpectedWinnerId(resultSet.getLong(EXPECTED_WINNER_ID));
                bet.setResultId(resultSet.getLong(RESULT_ID));
                bet.setUserId(resultSet.getLong(RESULT_ID));
                bets.add(bet);
            }
        } catch (InterruptedException | SQLException e) {
            getLogger().error("Something wrong happened while executing getAllIncompleteBetsByUser...", e);
        }

        return bets;
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
