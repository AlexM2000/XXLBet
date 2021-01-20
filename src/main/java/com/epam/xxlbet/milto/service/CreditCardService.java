package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.CreditCard;
import com.epam.xxlbet.milto.requestandresponsebody.UnlinkCreditCardRequest;

import java.util.List;

/**
 * CreditCardService.
 *
 * @author Aliaksei Milto
 */
public interface CreditCardService {

    /**
     * Link credit card to user.
     *
     * @param creditCard CreditCard
     * @return Linked {@link CreditCard} (taken from database)
     */
    CreditCard linkCreditCart(CreditCard creditCard);

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
     * Unlink credit card from user.
     *
     * @param request {@link UnlinkCreditCardRequest}
     */
    void unlinkCreditCard(UnlinkCreditCardRequest request);
}
