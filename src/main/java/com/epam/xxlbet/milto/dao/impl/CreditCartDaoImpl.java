package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.CreditCartDao;
import com.epam.xxlbet.milto.domain.CreditCard;
import com.epam.xxlbet.milto.populator.impl.ResultSetToCreditCardPopulator;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.*;

/**
 * CreditCartDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class CreditCartDaoImpl extends AbstractDaoImpl<CreditCard> implements CreditCartDao {
    private static CreditCartDaoImpl instance;

    private CreditCartDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_CREDIT_CARDS, ResultSetToCreditCardPopulator.getInstance());
    }

    public static CreditCartDaoImpl getInstance() {
        if (instance == null) {
            instance = new CreditCartDaoImpl();
        }

        return instance;
    }

    @Override
    public CreditCard createCreditCart(CreditCard creditCard) {
        executeUpdate(
                CREATE_CREDIT_CARD,
                creditCard.getNumber(),
                creditCard.getThru(),
                creditCard.getCvv(),
                creditCard.getUserId()
        );
        return getCreditCardByNumber(creditCard.getNumber());
    }

    @Override
    public CreditCard getCreditCardByNumber(String number) {
        return executeForSingleResult(SELECT_CREDIT_CARD_BY_NUMBER, number);
    }

    @Override
    public List<CreditCard> getCreditCardsByUserId(Long userId) {
        return executeQuery(SELECT_CREDIT_CARD_BY_USER, userId);
    }
}
