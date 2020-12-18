package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;

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
        System.out.println(registrationRequest.getEmail());
        user.setEmail(registrationRequest.getEmail());
    }
}