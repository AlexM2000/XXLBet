package com.emap.xxlbet.milto.service;

import com.emap.xxlbet.milto.domain.User;
import com.emap.xxlbet.milto.requestbody.RegistrationRequest;

public interface UserService {
    User createNewUser(RegistrationRequest registrationRequest);
    boolean checkIfUserExists(String email);
    User getUserByEmail(String email);
}
