package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.ConfirmationResult;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.requestandresponsebody.RegistrationRequest;

import java.util.List;

/**
 * UserService.
 *
 * @author Aliaksei Milto
 */
public interface UserService {

    /**
     * Find user by id
     *
     * @param id user id
     * @return {@link User}
     */
    User getUserById(Long id);

    /**
     * Create user in database from {@link RegistrationRequest}.
     * User password should be encrypted with {@link com.epam.xxlbet.milto.utils.cryptography.CryptoUtils}
     * before adding to database.
     *
     * @param registrationRequest {@link RegistrationRequest}
     * @return Created {@link User} (taken from database)
     */
    User createNewUser(RegistrationRequest registrationRequest);

    /**
     * Check if user with given phone number exists in database.
     *
     * @param phoneNumber phone number
     * @return true if exists, false otherwise
     */
    boolean isPhoneNumberExists(String phoneNumber);

    /**
     * Find user by email
     *
     * @param email user email
     * @return {@link User}
     */
    User getUserByEmail(String email);

    /**
     * Find user by email.
     * Implementations must encrypt password with {@link com.epam.xxlbet.milto.utils.cryptography.CryptoUtils}
     * and take secret key property from project.properties
     * to be able to find password in database.
     *
     * @param email user email
     * @param password user password
     * @return {@link User}
     */
    User getUserByEmailAndPassword(String email, String password);

    /**
     * Confirms registration of user to which belongs token.
     *
     * @param token token
     * @return {@link ConfirmationResult} depending on result of confirming registration
     */
    ConfirmationResult confirmRegistration(String token);

    /**
     * Find all users that did not confirm registration by email.
     *
     * @return all users that did not confirm registration by email
     */
    List<User> getAllUnconfirmedUsers();

    /**
     * Delete all users that did not confirm registration by email.
     */
    void deleteAllUnconfirmedUsers();

    /**
     * Update user.
     *
     * @param user User with updated data.
     * @return Updated user from database.
     */
    User updateUser(User user);
}
