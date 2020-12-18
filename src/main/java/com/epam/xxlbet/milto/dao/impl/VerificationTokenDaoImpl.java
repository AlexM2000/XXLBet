package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.VerificationTokenDao;
import com.epam.xxlbet.milto.domain.VerificationToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_EXPIRED_TOKENS_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_USER_TOKEN_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_VERIFICATION_TOKENS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_TOKEN_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_TOKEN_BY_TOKEN_PROPERTY_ID;

public class VerificationTokenDaoImpl extends AbstractDao implements VerificationTokenDao {
    private static VerificationTokenDaoImpl instance;

    private VerificationTokenDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_VERIFICATION_TOKENS);
    }

    public static VerificationTokenDaoImpl getInstance() {
        if (instance == null) {
            instance = new VerificationTokenDaoImpl();
        }

        return instance;
    }

    @Override
    public VerificationToken create(final VerificationToken token) {
        VerificationToken newToken = null;

        try(final Connection connection = getConnectionPool().getConnection()){
            final PreparedStatement statement = connection.prepareStatement(getSqlById(INSERT_TOKEN_PROPERTY_ID));

            statement.setString(1, token.getToken());
            statement.setTimestamp(2, new Timestamp(token.getExpiresAt().getTime()));
            statement.setLong(3, token.getUserId());

            statement.execute();
            statement.close();

            newToken = getVerificationTokenByToken(token.getToken());
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " insert...", e);
        }

        return newToken;
    }

    @Override
    public VerificationToken getVerificationTokenByToken(final String token) {
        VerificationToken neededToken = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(SELECT_TOKEN_BY_TOKEN_PROPERTY_ID));

            statement.setString(1, token);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                neededToken = new VerificationToken();
                neededToken.setId(resultSet.getLong("user_id"));
                neededToken.setToken(resultSet.getString("token"));
                neededToken.setExpiresAt(new Date(resultSet.getTimestamp("expires_at").getTime()));
                neededToken.setUserId(resultSet.getLong("user_id"));
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getVerificationTokenByToken...", e);
        }

        return neededToken;
    }

    @Override
    public void deleteAllExpiredTokens() {
        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(DELETE_EXPIRED_TOKENS_PROPERTY_ID));

            statement.execute();
            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " deleteAllExpiredTokens...", e);
        }
    }

    @Override
    public void deleteVerificationToken(Long userId) {
        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(DELETE_USER_TOKEN_PROPERTY_ID));

            statement.setLong(1, userId);

            statement.execute();
            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " deleteVerificationToken...", e);
        }
    }
}
