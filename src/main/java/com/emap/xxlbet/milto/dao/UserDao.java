package com.emap.xxlbet.milto.dao;

import com.emap.xxlbet.milto.domain.User;

public interface UserDao {
    User getUserById(long id);
    User getUserByEmail(String email);
    User getUserByPhoneNumber(String phoneNumber);
    User getUserByEmailAndPassword(String email, String password);
    User getUserByPhoneNumberAndPassword(String phoneNumber, String password);
    User createUser(User user);
    void deleteUser(String email, String phoneNumber);
    User updateUser(User user);
}
