package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.CreditCard;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToCreditCardPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToCreditCardPopulator implements ResultSetPopulator<CreditCard> {
    private static final String NUMBER = "number";
    private static final String THRU = "thru";
    private static final String CVV = "cvv";
    private static final String USER_ID = "user_id";
    private static ResultSetToCreditCardPopulator instance;

    private ResultSetToCreditCardPopulator() { }

    public static ResultSetToCreditCardPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToCreditCardPopulator();
        }

        return instance;
    }

    @Override
    public CreditCard populate(ResultSet source) throws SQLException {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(source.getString(NUMBER));
        creditCard.setThru(source.getString(THRU));
        creditCard.setCvv(source.getString(CVV));
        creditCard.setUserId(source.getLong(USER_ID));
        return creditCard;
    }
}
