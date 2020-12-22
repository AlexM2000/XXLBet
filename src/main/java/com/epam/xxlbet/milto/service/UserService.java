package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.ConfirmationResult;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;

public interface UserService {
    User getUserById(Long id);
    User createNewUser(RegistrationRequest registrationRequest);
    boolean checkIfUserExists(String email);
    boolean checkIfUserExistsByPhoneNumber(String phoneNumber);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password);
    ConfirmationResult confirmRegistration(String token);
}
