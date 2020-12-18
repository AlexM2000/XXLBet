package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.ConfirmationResult;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;

public interface UserService {
    User createNewUser(RegistrationRequest registrationRequest);
    boolean checkIfUserExists(String email);
    User getUserByEmail(String email);
    ConfirmationResult confirmRegistration(String token);
}
