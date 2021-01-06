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
    User getUserById(Long id);
    User createNewUser(RegistrationRequest registrationRequest);
    boolean checkIfUserExistsByPhoneNumber(String phoneNumber);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password);
    ConfirmationResult confirmRegistration(String token);
    List<User> getAllUnconfirmedUsers();
    void deleteAllUnconfirmedUsers();
}
