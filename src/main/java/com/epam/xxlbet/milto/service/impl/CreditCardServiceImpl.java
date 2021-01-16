package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.CreditCartDao;
import com.epam.xxlbet.milto.dao.impl.CreditCartDaoImpl;
import com.epam.xxlbet.milto.domain.CreditCard;
import com.epam.xxlbet.milto.service.CreditCardService;

import java.util.List;

/**
 * CreditCardServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class CreditCardServiceImpl implements CreditCardService {
    private static CreditCardServiceImpl instance;
    private CreditCartDao creditCartDao;

    private CreditCardServiceImpl() {
        this.creditCartDao = CreditCartDaoImpl.getInstance();
    }

    public static CreditCardServiceImpl getInstance() {
        if (instance == null) {
            instance = new CreditCardServiceImpl();
        }

        return instance;
    }

    @Override
    public CreditCard createCreditCart(CreditCard creditCard) {
        return creditCartDao.createCreditCart(creditCard);
    }

    @Override
    public CreditCard getCreditCardByNumber(String number) {
        return creditCartDao.getCreditCardByNumber(number);
    }

    @Override
    public List<CreditCard> getCreditCardsByUserId(Long userId) {
        return creditCartDao.getCreditCardsByUserId(userId);
    }
}
