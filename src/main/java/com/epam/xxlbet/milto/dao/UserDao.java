package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.User;

import java.util.List;

/**
 * UserDao.
 *
 * @author Aliaksei Milto
 */
public interface UserDao {
    User getUserById(long id);
    User getUserByEmail(String email);
    User getUserByPhoneNumber(String phoneNumber);
    User getUserByEmailAndPassword(String email, String password);
    User getUserByPhoneNumberAndPassword(String phoneNumber, String password);
    User createUser(User user);
    void deleteUser(String email, String phoneNumber);
    User updateUser(User user);
    void deleteAllUnconfirmedUsers();
    List<User> getAllUnconfirmedUsers();
}
