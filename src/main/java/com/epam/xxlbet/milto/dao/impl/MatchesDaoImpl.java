package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.dao.MatchesDao;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.domain.Opponent;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_ALL_FINISHED_MATCHES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_INTO_MATCHES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_INCOMPLETE_MATCHES_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_MATCHES_BY_TOURNAMENT;

/**
 * MatchesDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class MatchesDaoImpl extends AbstractDaoImpl<Match> implements MatchesDao {
    private static final String ID = "id";
    private static final String TOURNAMENT_ID = "tournament_id";
    private static final String DRAW_COEFFICIENT = "draw_coefficient";
    private static final String DATE_STARTED = "date_started";
    private static MatchesDaoImpl instance;

    private MatchesDaoImpl() {
        super(XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_MATCHES, null);
    }

    public static MatchesDaoImpl getInstance() {
        if (instance == null) {
            instance = new MatchesDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Match> getIncompleteMatches() {
        List<Match> matches = new ArrayList<>();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(SELECT_INCOMPLETE_MATCHES_PROPERTY_ID));

            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));

            ResultSet set = statement.executeQuery();
            long prevMatchId = 0;
            Match match;
            Set<Opponent> opponents = new LinkedHashSet<>();
            Opponent opponent;

            while (set.next()) {
                if (prevMatchId != set.getLong(5)) {
                    match = new Match();
                    opponents = new LinkedHashSet<>();
                    match.setDrawCoefficient(set.getBigDecimal(2));
                    match.setDateStarted(set.getTimestamp(1).toLocalDateTime());
                    match.setTournamentName(set.getString(6));
                    opponent = new Opponent();
                    opponent.setName(set.getString(3));
                    opponent.setCoefficient(set.getBigDecimal(4));
                    opponents.add(opponent);
                    match.setOpponents(opponents);
                    matches.add(match);
                } else {
                    opponent = new Opponent();
                    opponent.setName(set.getString(3));
                    opponent.setCoefficient(set.getBigDecimal(4));
                    opponents.add(opponent);
                }
                prevMatchId = set.getLong(5);
            }
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " in getIncompleteMatches", e);
        }

        return matches;
    }

    @Override
    public List<Match> getMatchesByTournament(String tournamentName) {
        List<Match> matches = new ArrayList<>();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(SELECT_MATCHES_BY_TOURNAMENT));

            statement.setString(1, tournamentName);

            ResultSet resultSet = statement.executeQuery();
            long prevMatchId = 0;
            Match match;
            Set<Opponent> opponents = new LinkedHashSet<>();
            Opponent opponent;

            while (resultSet.next()) {
                if (prevMatchId != resultSet.getLong(5)) {
                    match = new Match();
                    opponents = new LinkedHashSet<>();
                    match.setDrawCoefficient(resultSet.getBigDecimal(2));
                    match.setDateStarted(resultSet.getTimestamp(1).toLocalDateTime());
                    match.setTournamentName(resultSet.getString(6));
                    opponent = new Opponent();
                    opponent.setName(resultSet.getString(3));
                    opponent.setCoefficient(resultSet.getBigDecimal(4));
                    opponents.add(opponent);
                    match.setOpponents(opponents);
                    matches.add(match);
                } else {
                    opponent = new Opponent();
                    opponent.setName(resultSet.getString(3));
                    opponent.setCoefficient(resultSet.getBigDecimal(4));
                    opponents.add(opponent);
                }
                prevMatchId = resultSet.getLong(5);
            }

        } catch (InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " in getMatchesByTournament", e);
        }

        return matches;
    }

    @Override
    public List<Bet> getMatchesAfter(final LocalDateTime time) {
        return null;
    }

    @Override
    public long createMatch(Long tournamentId, BigDecimal drawCoefficient, LocalDateTime dateStarted) {
        executeUpdate(INSERT_INTO_MATCHES, tournamentId, drawCoefficient.doubleValue(), dateStarted);

        Long lastId = 0L;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById("select.last.match"));

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                lastId = resultSet.getLong(1);
            }

        } catch (InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " in createMatch ", e);
        }

        return lastId;
    }

    @Override
    public void deleteMatch(long id) {

    }

    @Override
    public void deleteAllFinishedMatches() {
        executeUpdate(DELETE_ALL_FINISHED_MATCHES);
    }
}
