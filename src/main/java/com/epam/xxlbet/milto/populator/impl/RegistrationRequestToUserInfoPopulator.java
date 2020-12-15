package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;

public class RegistrationRequestToUserInfoPopulator implements Populator<RegistrationRequest, UserInfo> {
    private static RegistrationRequestToUserInfoPopulator instance;

    private RegistrationRequestToUserInfoPopulator() { }

    public static RegistrationRequestToUserInfoPopulator getInstance() {
        if (instance == null) {
            instance = new RegistrationRequestToUserInfoPopulator();
        }

        return instance;
    }

    @Override
    public void populate(RegistrationRequest registrationRequest, UserInfo userInfo) {
        userInfo.setName(registrationRequest.getName());
        userInfo.setSecondName(registrationRequest.getSecondName());
        userInfo.setSurname(registrationRequest.getSurname());
        userInfo.setBirthDate(registrationRequest.getBirthDate());
        userInfo.setEmail(registrationRequest.getEmail());
        userInfo.setPhoneNumber(registrationRequest.getPhoneNumber());
    }
}
