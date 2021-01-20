package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.CreditCard;

import java.util.List;

/**
 * CreditCartsDao.
 *
 * @author Aliaksei Milto
 */
public interface CreditCartDao {

    /**
     * Create credit card info in database.
     *
     * @param creditCard CreditCard
     * @return Created {@link CreditCard} (taken from database)
     */
    CreditCard createCreditCard(CreditCard creditCard);

    /**
     * Find credit card by number.
     *
     * @param number card number
     * @return {@link CreditCard}
     */
    CreditCard getCreditCardByNumber(String number);

    /**
     * Find credit cards that belong to given user.
     *
     * @param userId user id
     * @return List of user cards.
     */
    List<CreditCard> getCreditCardsByUserId(Long userId);

    /**
     * Removes credit card from database.
     *
     * @param creditCard {@link CreditCard}
     */
    void removeCreditCard(CreditCard creditCard);
}
