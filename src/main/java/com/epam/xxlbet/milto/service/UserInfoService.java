package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.UserInfo;

public interface UserInfoService {
    UserInfo getUserInfoByEmail(String email);
}
