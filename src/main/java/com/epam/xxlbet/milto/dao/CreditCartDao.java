package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.CreditCard;

/**
 * CreditCartsDao.
 *
 * @author Aliaksei Milto
 */
public interface CreditCartDao {

    /**
     * Create credit cart info in database.
     *
     * @param creditCard CreditCard
     * @return Created {@link CreditCard} (taken from database)
     */
    CreditCartDao createCreditCart(CreditCard creditCard);
}
