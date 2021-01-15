package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.CreditCartDao;
import com.epam.xxlbet.milto.domain.CreditCard;
import com.epam.xxlbet.milto.populator.impl.ResultSetToCreditCardPopulator;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_CREDIT_CARDS;

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
    public CreditCartDao createCreditCart(CreditCard creditCard) {
        return null;
    }
}
