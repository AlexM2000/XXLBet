package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.dao.MatchesDao;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.domain.Opponent;

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

/**
 * MatchesDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class MatchesDaoImpl extends AbstractDaoImpl<Match> implements MatchesDao {
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
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.SELECT_INCOMPLETE_MATCHES_PROPERTY_ID));

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
    public List<Bet> getMatchesAfter(final LocalDateTime time) {
        return null;
    }

    @Override
    public Match createMatch(Match match) {
        return null;
    }

    @Override
    public void deleteMatch(long id) {

    }

    @Override
    public List<Match> getMatchesByTournament(long tournamentId) {
        return null;
    }
}
