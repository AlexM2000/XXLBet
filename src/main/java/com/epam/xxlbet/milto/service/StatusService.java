package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Status;

public interface StatusService {
    Status getUserStatusByEmail(String email);
}
