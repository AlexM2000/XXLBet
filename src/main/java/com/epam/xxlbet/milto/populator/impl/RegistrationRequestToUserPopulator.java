package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.requestandresponsebody.RegistrationRequest;

/**
 * RegistrationRequestToUserPopulator.
 *
 * @author Aliaksei Milto
 */
public class RegistrationRequestToUserPopulator implements Populator<RegistrationRequest, User> {
    private static RegistrationRequestToUserPopulator instance;

    private RegistrationRequestToUserPopulator() { }

    public static RegistrationRequestToUserPopulator getInstance() {
        if (instance == null) {
            instance = new RegistrationRequestToUserPopulator();
        }

        return instance;
    }

    @Override
    public void populate(RegistrationRequest registrationRequest, User user) {
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setEmail(registrationRequest.getEmail());
    }
}
