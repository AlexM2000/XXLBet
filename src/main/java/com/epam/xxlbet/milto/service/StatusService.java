package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Status;

/**
 * StatusService.
 *
 * @author Aliaksei Milto
 */
public interface StatusService {

    /**
     * Find status by status id.
     *
     * @param statusId status id
     * @return {@link Status}
     */
    Status getStatusById(Long statusId);

    /**
     * Get status of given user.
     *
     * @param email user email
     * @return user {@link Status}
     */
    Status getUserStatusByEmail(String email);

    /**
     * Find status by status name.
     *
     * @param name status name
     * @return {@link Status}
     */
    Status getUserStatusByName(String name);
}
