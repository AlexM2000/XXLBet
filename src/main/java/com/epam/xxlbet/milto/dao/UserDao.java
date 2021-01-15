package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.User;

import java.util.List;

/**
 * UserDao.
 *
 * @author Aliaksei Milto
 */
public interface UserDao {

    /**
     * Find user by id
     *
     * @param id user id
     * @return {@link User}
     */
    User getUserById(long id);

    /**
     * Find user by email
     *
     * @param email user email
     * @return {@link User}
     */
    User getUserByEmail(String email);

    /**
     * Find user by phone number.
     *
     * @param phoneNumber user phone number
     * @return {@link User}
     */
    User getUserByPhoneNumber(String phoneNumber);

    /**
     * Find user by phone number.
     *
     * @param email user email
     * @param password user password
     * @return {@link User}
     */
    User getUserByEmailAndPassword(String email, String password);

    /**
     * Create user in database.
     *
     * @param user {@link User}
     * @return Created {@link User} (taken from database)
     */
    User createUser(User user);

    /**
     * Delete user from database by email and phone number.
     *
     * @param email user email
     * @param phoneNumber user phone number
     */
    void deleteUser(String email, String phoneNumber);

    /**
     * Update user in database.
     *
     * @param user {@link User} with updated data
     * @return Updated {@link User} (taken from database)
     */
    User updateUser(User user);

    /**
     * Delete all users that did not confirm registration by email.
     */
    void deleteAllUnconfirmedUsers();

    /**
     * Find all users that did not confirm registration by email.
     *
     * @return all users that did not confirm registration by email
     */
    List<User> getAllUnconfirmedUsers();
}
